package com.autospanish.app.study

import com.autospanish.app.data.WordEntity

enum class ReviewRating(val quality: Int) {
    AGAIN(1),
    HARD(3),
    GOOD(4),
    EASY(5),
}

object SpacedRepetition {
    private const val ONE_DAY_MS = 86_400_000L

    fun applyReview(
        word: WordEntity,
        rating: ReviewRating,
        nowMs: Long = System.currentTimeMillis(),
    ): WordEntity {
        val q = rating.quality
        val wasLapse = q < 3

        val newRepetitions = if (wasLapse) 0 else word.repetitions + 1
        val newLapseCount = if (wasLapse && word.repetitions > 0) word.lapseCount + 1 else word.lapseCount

        val newIntervalDays = when {
            wasLapse -> 1
            newRepetitions == 1 -> 1
            newRepetitions == 2 -> when (rating) {
                ReviewRating.HARD -> 2
                ReviewRating.GOOD -> 4
                ReviewRating.EASY -> 6
                else -> 4
            }
            else -> {
                val factor = when (rating) {
                    ReviewRating.HARD -> 1.2f
                    ReviewRating.GOOD -> word.easeFactor
                    ReviewRating.EASY -> word.easeFactor * 1.3f
                    else -> word.easeFactor
                }
                val calculated = (word.intervalDays * factor).toInt()
                maxOf(word.intervalDays + 1, calculated)
            }
        }

        val delta = 0.1f - (5 - q) * (0.08f + (5 - q) * 0.02f)
        val newEase = maxOf(1.3f, word.easeFactor + delta)
        val newNextReviewDate = nowMs + (newIntervalDays.toLong() * ONE_DAY_MS)

        return word.copy(
            timesTried = word.timesTried + 1,
            timesCorrect = word.timesCorrect + if (wasLapse) 0 else 1,
            nextReviewDate = newNextReviewDate,
            intervalDays = newIntervalDays,
            easeFactor = newEase,
            repetitions = newRepetitions,
            lapseCount = newLapseCount,
            lastReviewedDate = nowMs,
        )
    }
}
