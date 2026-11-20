package com.autospanish.app.translate

import org.junit.Assert.assertTrue
import org.junit.Test

/** Tests that ambiguous words resolve to one sense and keep others as synonyms. */
class AmbiguousWordTest {

    @Test
    fun `orange resolves to one sense and keeps the other as a synonym`() {
        val matches = DictionaryTestFixture.byEnglish["orange"]
        assertTrue("expected dictionary entries for \"orange\"", !matches.isNullOrEmpty())
        val resolution = SenseResolver.resolve("orange", matches!!)
        assertTrue("expected the other sense to survive as a synonym, got $resolution", resolution.extraSynonyms.isNotEmpty())
    }
}
