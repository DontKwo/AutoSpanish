package com.autospanish.app.grammar

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TextNormalizationTest {

    @Test
    fun `stripAccents removes Spanish acute accents and diaeresis`() {
        assertEquals("arbol", stripAccents("árbol"))
        assertEquals("cancion", stripAccents("canción"))
        assertEquals("musica", stripAccents("música"))
        assertEquals("pinguino", stripAccents("pingüino"))
        assertEquals("facil", stripAccents("fácil"))
        assertEquals("telefono", stripAccents("teléfono"))
    }

    @Test
    fun `stripAccents folds n with tilde`() {
        assertEquals("nino", stripAccents("niño"))
        assertEquals("espanol", stripAccents("español"))
        assertEquals("ano", stripAccents("año"))
    }

    @Test
    fun `stripAccents leaves standard ASCII letters unchanged`() {
        assertEquals("casa", stripAccents("casa"))
        assertEquals("perro", stripAccents("perro"))
        assertEquals("hablar", stripAccents("hablar"))
    }

    @Test
    fun `accent-insensitive matching succeeds for queries with or without accents`() {
        val target = "Árbol"
        val queryWithoutAccent = "arbol"
        val queryWithAccent = "árbol"

        val normalizedTarget = stripAccents(target.lowercase())
        assertTrue(normalizedTarget.contains(stripAccents(queryWithoutAccent.lowercase())))
        assertTrue(normalizedTarget.contains(stripAccents(queryWithAccent.lowercase())))
    }
}
