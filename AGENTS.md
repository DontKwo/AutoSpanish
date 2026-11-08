# AI Agent Operational Reference

## Architecture

*   **Translation Modules:**
    *   `WordGenerationPipeline.kt`: Central pipeline that handles translating and enriching words/phrases, combining data from dictionaries, ML Kit, and MyMemory.
    *   `MlKitTranslateService.kt`: Handles on-device translation and language identification using Google ML Kit.
    *   `MyMemoryApi.kt`: Provides fallback online translation using the MyMemory API.
*   **Vocabulary Storage:**
    *   `AppDatabase.kt`: Room database to store the user's generated flashcards and lists.
    *   `AssetDictionaries.kt`: Manages access to bundled JSON dictionary files (extracted from Wiktionary data).
*   **Audio Pipeline:**
    *   As per `REQUIREMENTS.md`, there is **no audio/pronunciation/TTS pipeline** anywhere in the application.
*   **Test Runner:**
    *   The project uses standard JUnit tests.
    *   Tests can be executed via the Gradle wrapper command: `./gradlew test`

## CLI Entry Points and Arguments

As this is a standard Android application, it lacks traditional Command Line Interface (CLI) entry points for its core features.

The primary command line usage is for building, testing, and installing the application via the Gradle wrapper (`gradlew`):
*   `./gradlew assembleDebug`: Builds the debug APK.
*   `./gradlew test`: Runs the test suite.
*   `./build-apk.sh`: Helper script to build the debug APK and copy it to the root directory using the project-local toolchain.
