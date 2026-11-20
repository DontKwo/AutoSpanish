package com.autospanish.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val spanish: List<String>,
    val englishMeanings: List<String>,
    val partOfSpeech: PartOfSpeech,
    val gender: Gender = Gender.NONE,
    val article: String? = null,
    val plural: String? = null,
    val isReflexive: Boolean = false,
    val counterpartForm: String? = null,
    val irregularityType: String? = null,
    val conjugations: Map<String, List<String>> = emptyMap(),
    val serEstarHint: String? = null,
    val falseFriendWarning: String? = null,
    val synonyms: List<String> = emptyList(),
    val regionalNote: String? = null,
    val exampleSentences: List<String> = emptyList(),
    val prepositionPairing: String? = null,
    val dateAdded: Long = System.currentTimeMillis(),
    val timesCorrect: Int = 0,
    val timesTried: Int = 0,
    val nextReviewDate: Long = 0L,
    val intervalDays: Int = 0,
    val easeFactor: Float = 2.5f,
    val repetitions: Int = 0,
    val lapseCount: Int = 0,
    val lastReviewedDate: Long = 0L,
) {
    val masteryPercent: Int
        get() = if (timesTried == 0) 0 else (timesCorrect * 100) / timesTried

    val retentionRate: Float
        get() = if (timesTried == 0) 0f else timesCorrect.toFloat() / timesTried

    val isLeech: Boolean
        get() = lapseCount >= 3 || (timesTried >= 4 && masteryPercent < 50)

    val isDueForReview: Boolean
        get() = timesTried > 0 && nextReviewDate > 0L && nextReviewDate <= System.currentTimeMillis()
}
