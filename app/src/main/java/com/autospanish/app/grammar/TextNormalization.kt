package com.autospanish.app.grammar

import java.text.Normalizer

/** Strips combining diacritical marks from text, folding á, é, í, ó, ú, ü, and ñ to base characters. */
fun stripAccents(text: String): String {
    val normalized = Normalizer.normalize(text, Normalizer.Form.NFD)
    return normalized.replace("\\p{M}".toRegex(), "")
}
