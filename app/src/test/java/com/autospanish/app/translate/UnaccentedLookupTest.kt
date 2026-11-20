package com.autospanish.app.translate

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class UnaccentedLookupTest {

    @Test
    fun `resolves Spanish word typed without accent to accented headword`() {
        val entry = DictionaryTestFixture.resolveSpanish("arbol")
        assertNotNull("Expected resolution for unaccented 'arbol'", entry)
        assertEquals("árbol", entry?.spanish)
    }

    @Test
    fun `resolves Spanish word typed with accent to accented headword`() {
        val entry = DictionaryTestFixture.resolveSpanish("árbol")
        assertNotNull("Expected resolution for accented 'árbol'", entry)
        assertEquals("árbol", entry?.spanish)
    }

    @Test
    fun `resolves unaccented cancion to cancion with accent`() {
        val entry = DictionaryTestFixture.resolveSpanish("cancion")
        assertNotNull("Expected resolution for unaccented 'cancion'", entry)
        assertEquals("canción", entry?.spanish)
    }

    @Test
    fun `resolves unaccented musica to musica with accent`() {
        val entry = DictionaryTestFixture.resolveSpanish("musica")
        assertNotNull("Expected resolution for unaccented 'musica'", entry)
        assertEquals("música", entry?.spanish)
    }
}
