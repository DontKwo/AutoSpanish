# AutoSpanish User Guide

Welcome to the AutoSpanish User Guide! This document will walk you through the core features of the app and help you start learning effectively.

## Vocabulary Generation

AutoSpanish creates flashcards for you as you encounter new words.

### Translating Text
1. Navigate to the **Home** or **Search** screen.
2. Type in a word or phrase in either English or Spanish.
3. The app's `WordGenerationPipeline` will automatically resolve the language, lookup the word, and generate a fully-annotated flashcard containing gender, plural forms, example sentences, conjugations, and more.
4. Save the generated word to a specific list.

### Scanning Text (OCR)
1. Tap the **Camera** icon to open the OCR feature.
2. Point your device's camera at text containing the word you want to learn.
3. The app will recognize the text, and you can select the word to immediately translate and save it as a flashcard.

## Study Sessions

AutoSpanish makes reviewing vocabulary simple. There's no separate "deck" concept; you study directly from the lists you build.

1. Navigate to your desired word list.
2. Select either **Flashcards** or **Learn Mode**.
    *   **Flashcards:** Traditional flip-cards for a quick review.
    *   **Learn Mode:** A Quizlet-style mode to test your knowledge more rigorously.

## Exporting Lists

You can export your lists in several formats so you can review them or share them however you like:

1. Go to a specific list.
2. Tap the Export option.
3. Choose one of the following formats:
    *   **CSV:** Standard comma-separated values format for spreadsheets.
    *   **Quizlet:** Text formatted for easy import into Quizlet.
    *   **PDF:** A printable document of your vocabulary list.
    *   **DOCX:** A Microsoft Word document.

*(Export functionality is handled via the specific exporters found in the `com.autospanish.app.export` package.)*
