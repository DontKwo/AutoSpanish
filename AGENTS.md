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
    *   There is **no audio/pronunciation/TTS pipeline** anywhere in the application.
*   **Test Runner:**
    *   The project uses standard JUnit tests.
    *   Tests can be executed via the Gradle wrapper command: `./gradlew test`

## CLI Entry Points and Arguments

As this is a standard Android application, it lacks traditional Command Line Interface (CLI) entry points for its core features.

The primary command line usage is for building and testing through the Gradle wrapper (`gradlew`):
* `./gradlew test`: Runs the JUnit test suite.
* `./gradlew assembleRelease`: Builds the release APK when keystore environment variables are configured.

## Documentation & Linting Rules

* Keep `README.md` user-focused (`<= 120` lines), avoid checksums or CI prose, and keep [Android APK Installation Guide](docs/APK_INSTALLATION.md) linked prominently.
* Keep `## Related Documentation` footers linking back to `../README.md` on all files under `docs/`.

## Related Documentation

* [Project Overview](README.md)
* [Developer Guide](docs/DEVELOPMENT.md)
* [User Guide](docs/USER_GUIDE.md)
* [Security Policy](SECURITY.md)
