package com.autospanish.app.study

import com.autospanish.app.data.PartOfSpeech
import com.autospanish.app.data.WordEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SpacedRepetitionTest {

    private fun testWord() = WordEntity(
        id = 1L,
        spanish = listOf("árbol"),
        englishMeanings = listOf("tree"),
        partOfSpeech = PartOfSpeech.NOUN,
    )

    @Test
    fun `initial successful review sets interval and increments repetitions`() {
        val word = testWord()
        val now = 1_000_000L
        val reviewed = SpacedRepetition.applyReview(word, ReviewRating.GOOD, now)

        assertEquals(1, reviewed.timesTried)
        assertEquals(1, reviewed.timesCorrect)
        assertEquals(1, reviewed.repetitions)
        assertEquals(1, reviewed.intervalDays)
        assertEquals(now + 86_400_000L, reviewed.nextReviewDate)
        assertEquals(0, reviewed.lapseCount)
    }

    @Test
    fun `consecutive successful reviews grow interval`() {
        val word = testWord()
        val now = 1_000_000L

        val rev1 = SpacedRepetition.applyReview(word, ReviewRating.GOOD, now)
        val rev2 = SpacedRepetition.applyReview(rev1, ReviewRating.GOOD, now + 86_400_000L)
        val rev3 = SpacedRepetition.applyReview(rev2, ReviewRating.GOOD, now + 86_400_000L * 5)

        assertEquals(2, rev2.repetitions)
        assertEquals(4, rev2.intervalDays)

        assertEquals(3, rev3.repetitions)
        assertTrue(rev3.intervalDays >= 9)
    }

    @Test
    fun `again rating triggers lapse and resets interval to one day`() {
        val word = testWord()
        val now = 1_000_000L

        val rev1 = SpacedRepetition.applyReview(word, ReviewRating.GOOD, now)
        val rev2 = SpacedRepetition.applyReview(rev1, ReviewRating.GOOD, now + 86_400_000L)
        val lapsed = SpacedRepetition.applyReview(rev2, ReviewRating.AGAIN, now + 86_400_000L * 5)

        assertEquals(0, lapsed.repetitions)
        assertEquals(1, lapsed.intervalDays)
        assertEquals(1, lapsed.lapseCount)
        assertEquals(2, lapsed.timesCorrect)
        assertEquals(3, lapsed.timesTried)
    }

    @Test
    fun `leech is detected after three lapses`() {
        var word = testWord()
        var now = 1_000_000L

        assertFalse(word.isLeech)
        for (i in 1..3) {
            word = SpacedRepetition.applyReview(word, ReviewRating.GOOD, now)
            now += 86_400_000L
            word = SpacedRepetition.applyReview(word, ReviewRating.AGAIN, now)
            now += 86_400_000L
        }

        assertTrue(word.isLeech)
    }

    @Test
    fun `due for review is true when nextReviewDate is in the past`() {
        val word = testWord()
        val now = 2_000_000_000L
        val reviewed = SpacedRepetition.applyReview(word, ReviewRating.GOOD, now)

        assertFalse(reviewed.isDueForReview)
        val pastDue = reviewed.copy(nextReviewDate = System.currentTimeMillis() - 1000L)
        assertTrue(pastDue.isDueForReview)
    }
}
