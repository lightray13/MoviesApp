package com.testing.moviesapp.util

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun Int?.releaseYearString(): String {
    return this?.let {
        "( $this )"
    } ?: ""
}

fun String?.trimParanthesis(): String {
    return this?.replace(Regex("[\\[(ActorName=)\\]]"), "") ?: ""
}