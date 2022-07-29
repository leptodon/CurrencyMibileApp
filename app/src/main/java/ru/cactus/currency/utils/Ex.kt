package ru.cactus.currency.utils

fun String?.toCorrectFloat():Float{
    var str = 0.0f
    if (this != null) {
        try {
            str = this.toFloat()
        } catch (e: Exception) {

        }
    }
    return str
}