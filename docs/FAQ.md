# AutoSpanish FAQ

Frequently asked questions regarding AutoSpanish features and functionality.

## Pronunciation and Audio

**Q: Does AutoSpanish have an audio pronunciation feature?**

A: No. As per the strict design requirements of the application, there are no audio, pronunciation, or Text-to-Speech (TTS) features anywhere in the app.

## Conjugation Rules

**Q: How does AutoSpanish handle verb conjugations?**

A: AutoSpanish ships with an extensive, built-in Spanish dictionary (extracted from Wiktionary data). For verbs, the app primarily looks up the complete conjugation table directly from this dictionary data.
If a specific verb's table is not present in the dictionary, the app dynamically generates the conjugations using the `ConjugationEngine.kt` fallback, which utilizes rules and data from `AssetDictionaries.kt`.

## Deck Import and Data

**Q: Can I import Anki decks or other flashcard sets?**

A: No, AutoSpanish does not support importing standard flashcard "decks." The app is designed around generating words individually from dictionary data to provide rich grammatical context, rather than importing simple front/back cards.

However, you *can* backup and restore your entire AutoSpanish database (lists and words) as a single JSON payload using the Backup feature (managed by `BackupManager.kt`).
