package com.autospanish.app.grammar

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ConjugationEngineTest {

    @Test
    fun `regular ar verb conjugates with future conditional and subjunctive`() {
        val result = ConjugationEngine.generateRegular("hablar")
        assertEquals(listOf("hablo", "hablas", "habla", "hablamos", "habláis", "hablan"), result["present"])
        assertEquals(listOf("hablé", "hablaste", "habló", "hablamos", "hablasteis", "hablaron"), result["preterite"])
        assertEquals(listOf("hablaba", "hablabas", "hablaba", "hablábamos", "hablabais", "hablaban"), result["imperfect"])
        assertEquals(listOf("hablaré", "hablarás", "hablará", "hablaremos", "hablaréis", "hablarán"), result["future"])
        assertEquals(listOf("hablaría", "hablarías", "hablaría", "hablaríamos", "hablaríais", "hablarían"), result["conditional"])
        assertEquals(listOf("hable", "hables", "hable", "hablemos", "habléis", "hablen"), result["subjunctive_present"])
    }

    @Test
    fun `regular er verb conjugates with future conditional and subjunctive`() {
        val result = ConjugationEngine.generateRegular("comer")
        assertEquals(listOf("como", "comes", "come", "comemos", "coméis", "comen"), result["present"])
        assertEquals(listOf("coma", "comas", "coma", "comamos", "comáis", "coman"), result["subjunctive_present"])
    }

    @Test
    fun `reflexive irregular verb applies reflexive pronouns to irregular forms`() {
        val dormirData = IrregularVerbData(
            type = "o -> ue stem change",
            tenses = listOf("present"),
            present = listOf("duermo", "duermes", "duerme", "dormimos", "dormís", "duermen"),
            english = listOf("sleep"),
        )
        val irregulars = mapOf("dormir" to dormirData)

        val result = ConjugationEngine.conjugate("dormirse", irregulars)
        assertTrue(result.isReflexive)
        assertEquals("o -> ue stem change", result.irregularityType)
        assertNotNull(result.conjugations["present"])
        assertEquals(
            listOf("me duermo", "te duermes", "se duerme", "nos dormimos", "os dormís", "se duermen"),
            result.conjugations["present"],
        )
    }

    @Test
    fun `reflexive regular verb attaches reflexive pronouns to regular forms`() {
        val result = ConjugationEngine.conjugate("lavarse", emptyMap())
        assertTrue(result.isReflexive)
        assertEquals(
            listOf("me lavo", "te lavas", "se lava", "nos lavamos", "os laváis", "se lavan"),
            result.conjugations["present"],
        )
    }
}
