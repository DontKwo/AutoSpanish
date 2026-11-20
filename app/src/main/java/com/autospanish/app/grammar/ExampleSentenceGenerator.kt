package com.autospanish.app.grammar

object ExampleSentenceGenerator {

    /** Returns sourced example sentences. */
    fun generate(dictEntry: DictionaryEntry?): List<String> = dictEntry?.exampleSentences ?: emptyList()
}
