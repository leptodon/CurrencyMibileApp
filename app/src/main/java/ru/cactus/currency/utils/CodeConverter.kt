package ru.cactus.currency.utils

fun String.toCountryCode(): String = codeMap[this].toString()

val codeMap = mapOf(
    "a" to "a"
)