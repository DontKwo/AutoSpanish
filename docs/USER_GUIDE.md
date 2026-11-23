# AutoSpanish User Guide

Learn how to generate grammar-annotated flashcards, scan Spanish text, study lists, and export your vocabulary in AutoSpanish.

## Installing on Android

Download `AutoSpanish-Android.apk` from [Latest Releases](https://github.com/DontKwo/AutoSpanish/releases/latest) and follow the [Android APK Installation Guide](APK_INSTALLATION.md).

## Vocabulary Generation & Offline Dictionary

1. Open **Home** or **Search** and type an English or Spanish word or phrase.
2. `AutoSpanish` checks its bundled offline Spanish dictionary (~78,900 headwords extracted from Kaikki/Wiktionary alongside curated irregular verbs, false-friend warnings, `ser`/`estar` shifts, prepositions, and regional variants) first.
3. Each generated flashcard includes noun gender and articles (`el`/`la`), plural forms, gendered pairs, conjugated verb tables, example sentences, synonyms, and false-friend warnings.
4. For words outside the bundled dictionary, the app falls back to on-device Google ML Kit (downloadable in in-app **Settings** for offline use) or the MyMemory online API when connected.

## Scanning Text (OCR)

1. Tap **Camera / Scan** and grant camera permission when prompted.
2. Frame printed Spanish or English text and tap any detected word to translate and save it into a list.

## Study Modes & Exporting Lists

- **Flashcards**: Classic two-sided flip cards for rapid recall.
- **Learn Mode**: Interactive multi-format drill mode organized directly around your word lists.
- **Export & Backup**: Export any list as CSV, Quizlet text, printable PDF, or DOCX, or export/restore a full JSON backup under **Settings**.

## Related Documentation

- [Project Overview](../README.md)
- [Android APK Installation Guide](APK_INSTALLATION.md)
- [Frequently Asked Questions](FAQ.md)
- [Developer Guide](DEVELOPMENT.md)
- [Third-Party Notices & Data Provenance](../THIRD_PARTY_NOTICES.md)
- [Security Policy](../SECURITY.md)
