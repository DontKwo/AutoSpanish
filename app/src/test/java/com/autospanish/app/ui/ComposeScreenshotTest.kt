package com.autospanish.app.ui

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.autospanish.app.data.AppDatabase
import com.autospanish.app.data.GLOBAL_LIST_ID
import com.autospanish.app.data.Gender
import com.autospanish.app.data.ListWordCrossRef
import com.autospanish.app.data.PartOfSpeech
import com.autospanish.app.data.Repository
import com.autospanish.app.data.WordEntity
import com.autospanish.app.data.WordListEntity
import com.autospanish.app.export.BackupManager
import com.autospanish.app.grammar.AssetDictionaries
import com.autospanish.app.settings.SettingsDataStore
import com.autospanish.app.translate.MlKitTranslateService
import com.autospanish.app.translate.WordGenerationPipeline
import com.autospanish.app.ui.screens.FlashcardStudyScreen
import com.autospanish.app.ui.screens.HomeScreen
import com.autospanish.app.ui.theme.AutoSpanishTheme
import java.io.File
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Renders Compose screens via Robolectric Native Graphics and writes PNG screenshots. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], qualifiers = "w411dp-h891dp-420dpi", application = Application::class)
class ComposeScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var database: AppDatabase
    private lateinit var viewModel: AppViewModel

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val wordDao = database.wordDao()
        val listDao = database.listDao()

        runBlocking {
            val now = System.currentTimeMillis()
            listDao.insert(WordListEntity(id = GLOBAL_LIST_ID, name = "All Words", isGlobal = true, sortOrder = 0))
            val travelListId = listDao.insert(WordListEntity(name = "Travel Essentials", sortOrder = 1))
            val verbsListId = listDao.insert(WordListEntity(name = "Daily Verbs", sortOrder = 2))

            val sampleWords = listOf(
                WordEntity(
                    spanish = listOf("madrugada"),
                    englishMeanings = listOf("early morning", "dawn"),
                    partOfSpeech = PartOfSpeech.NOUN,
                    gender = Gender.FEMININE,
                    article = "la",
                    plural = "madrugadas",
                    exampleSentences = listOf("Salimos de viaje de madrugada."),
                    timesCorrect = 3,
                    timesTried = 4,
                    nextReviewDate = now - 3_600_000L,
                    intervalDays = 1,
                    repetitions = 1,
                ),
                WordEntity(
                    spanish = listOf("equipaje"),
                    englishMeanings = listOf("luggage", "baggage"),
                    partOfSpeech = PartOfSpeech.NOUN,
                    gender = Gender.MASCULINE,
                    article = "el",
                    plural = "equipajes",
                    exampleSentences = listOf("Mi equipaje ya está en el hotel."),
                    timesCorrect = 4,
                    timesTried = 5,
                    nextReviewDate = now - 1_800_000L,
                    intervalDays = 2,
                    repetitions = 2,
                ),
                WordEntity(
                    spanish = listOf("madrugar"),
                    englishMeanings = listOf("to wake up early"),
                    partOfSpeech = PartOfSpeech.VERB,
                    conjugations = mapOf("Presente" to listOf("madrugo", "madrugas", "madruga")),
                    exampleSentences = listOf("Mañana necesito madrugar para el vuelo."),
                    timesCorrect = 2,
                    timesTried = 3,
                    nextReviewDate = now + 86_400_000L,
                    intervalDays = 1,
                    repetitions = 1,
                ),
            )

            sampleWords.forEachIndexed { idx, word ->
                val wordId = wordDao.insert(word)
                wordDao.insertListCrossRef(ListWordCrossRef(GLOBAL_LIST_ID, wordId))
                val targetList = if (idx < 2) travelListId else verbsListId
                wordDao.insertListCrossRef(ListWordCrossRef(targetList, wordId))
            }
        }

        val pipeline = WordGenerationPipeline(AssetDictionaries.getInstance(context), MlKitTranslateService())
        val repository = Repository(wordDao, listDao, pipeline)
        val backupManager = BackupManager(wordDao, listDao)
        val settingsDataStore = SettingsDataStore(context)
        viewModel = AppViewModel(repository, backupManager, settingsDataStore, MlKitTranslateService())
    }

    @After
    fun tearDown() {
        database.close()
    }

    private fun resolveScreenshotDir(): File {
        val dir = File("app/build/screenshots").takeIf { File("app").isDirectory } ?: File("build/screenshots")
        dir.mkdirs()
        return dir
    }

    private fun saveViewScreenshot(hostView: View?, filename: String) {
        var capturedBitmap: Bitmap? = null
        composeRule.runOnUiThread {
            val view = requireNotNull(hostView) { "Host view was not attached before screenshot capture" }
            val rootView = view.rootView ?: view
            if (rootView.width <= 0 || rootView.height <= 0) {
                val widthSpec = View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY)
                val heightSpec = View.MeasureSpec.makeMeasureSpec(2340, View.MeasureSpec.EXACTLY)
                rootView.measure(widthSpec, heightSpec)
                rootView.layout(0, 0, rootView.measuredWidth, rootView.measuredHeight)
            }
            val bitmap = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
            rootView.draw(Canvas(bitmap))
            capturedBitmap = bitmap
        }
        val bitmap = requireNotNull(capturedBitmap)
        assertTrue("Expected realistic phone bitmap dimensions: ${bitmap.width}x${bitmap.height}", bitmap.width >= 500 && bitmap.height >= 1000)
        val targetFile = File(resolveScreenshotDir(), filename)
        targetFile.outputStream().use { stream ->
            assertTrue(bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream))
        }
        assertTrue(targetFile.length() > 2048L)
    }

    @Test
    fun captureHomeScreen() {
        var hostView: View? = null
        composeRule.setContent {
            hostView = LocalView.current
            AutoSpanishTheme(darkModeOverride = 2) {
                Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
                    HomeScreen(
                        viewModel = viewModel,
                        onOpenList = {},
                        onOpenSearch = {},
                        onOpenSettings = {},
                        onQuickAdd = {},
                        onStudySmart = {},
                        onLearnSmart = {},
                    )
                }
            }
        }
        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule.onAllNodesWithText("Travel Essentials").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.waitForIdle()
        saveViewScreenshot(hostView, "01_main.png")
    }

    @Test
    fun captureStudyScreen() {
        var hostView: View? = null
        composeRule.setContent {
            hostView = LocalView.current
            AutoSpanishTheme(darkModeOverride = 2) {
                Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
                    FlashcardStudyScreen(
                        viewModel = viewModel,
                        source = "list_$GLOBAL_LIST_ID",
                        onBack = {},
                    )
                }
            }
        }
        composeRule.waitUntil(timeoutMillis = 10_000L) {
            composeRule.onAllNodesWithText("la madrugada", substring = true).fetchSemanticsNodes().isNotEmpty() ||
                composeRule.onAllNodesWithText("el equipaje", substring = true).fetchSemanticsNodes().isNotEmpty() ||
                composeRule.onAllNodesWithText("madrugar", substring = true).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.waitForIdle()
        saveViewScreenshot(hostView, "02_study.png")
    }
}
