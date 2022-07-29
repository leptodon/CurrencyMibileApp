package ru.cactus.currency.utils

import android.text.Html
import java.util.*

fun String?.toCorrectFloat(): Float {
    var str = 0.0f
    if (this != null) {
        try {
            str = this.toFloat()
        } catch (e: Exception) {

        }
    }
    return str
}

fun String.toImgSymbol(): String {
    return try {
        Html.fromHtml(map[this], Html.FROM_HTML_MODE_COMPACT).toString()
    } catch (e: Exception) {
        Currency.getInstance(this).getSymbol(Locale.US).toString()
    }
}

val map = mapOf(
    "ALL" to "&#76;&#101;&#107;",
    "AFN" to "&#1547;",
    "ARS" to "&#36;",
    "AWG" to "&#402;",
    "AUD" to "&#36;",
    "AZN" to "&#1084;&#1072;&#1085;",
    "BSD" to "&#36;",
    "BBD" to "&#36;",
    "BYR" to "&#112;&#46;",
    "BZD" to "&#66;&#90;&#36;",
    "BMD" to "&#36;",
    "BOB" to "&#36;&#98;",
    "BAM" to "&#75;&#77;",
    "BWP" to "&#80;",
    "BGN" to "&#1083;&#1074;",
    "BRL" to "&#82;&#36;",
    "BND" to "&#36;",
    "KHR" to "&#6107;",
    "CAD" to "&#36;",
    "KYD" to "&#36;",
    "CLP" to "&#36;",
    "CNY" to "&#165;",
    "COP" to "&#36;",
    "CRC" to "&#8353;",
    "HRK" to "&#107;&#110;",
    "CUP" to "&#8369;",
    "CZK" to "&#75;&#269;",
    "DKK" to "&#107;&#114;",
    "DOP" to "&#82;&#68;&#36;",
    "XCD" to "&#36;",
    "EGP" to "&#163;",
    "SVC" to "&#36;",
    "EEK" to "&#107;&#114;",
    "EUR" to "&#8364;",
    "FKP" to "&#163;",
    "FJD" to "&#36;",
    "GHC" to "&#162;",
    "GIP" to "&#163;",
    "GTQ" to "&#81;",
    "GGP" to "&#163;",
    "GYD" to "&#36;",
    "HNL" to "&#76;",
    "HKD" to "&#36;",
    "HUF" to "&#70;&#116;",
    "ISK" to "&#107;&#114;",
    "INR" to "&#8377;",
    "IDR" to "&#82;&#112;",
    "IRR" to "&#65020;",
    "IMP" to "&#163;",
    "ILS" to "&#8362;",
    "JMD" to "&#74;&#36;",
    "JPY" to "&#165;",
    "JEP" to "&#163;",
    "KZT" to "&#1083;&#1074;",
    "KPW" to "&#8361;",
    "KRW" to "&#8361;",
    "KGS" to "&#1083;&#1074;",
    "LAK" to "&#8365;",
    "LVL" to "&#76;&#115;",
    "LBP" to "&#163;",
    "LRD" to "&#36;",
    "LTL" to "&#76;&#116;",
    "MKD" to "&#1076;&#1077;&#1085;",
    "MYR" to "&#82;&#77;",
    "MUR" to "&#8360;",
    "MXN" to "&#36;",
    "MNT" to "&#8366;",
    "MZN" to "&#77;&#84;",
    "NAD" to "&#36;",
    "NPR" to "&#8360;",
    "ANG" to "&#402;",
    "NZD" to "&#36;",
    "NIO" to "&#67;&#36;",
    "NGN" to "&#8358;",
    "NOK" to "&#107;&#114;",
    "OMR" to "&#65020;",
    "PKR" to "&#8360;",
    "PAB" to "&#66;&#47;&#46;",
    "PYG" to "&#71;&#115;",
    "PEN" to "&#83;&#47;&#46;",
    "PHP" to "&#8369;",
    "PLN" to "&#122;&#322;",
    "QAR" to "&#65020;",
    "RON" to "&#108;&#101;&#105;",
    "RUB" to "&#1088;&#1091;&#1073;",
    "SHP" to "&#163;",
    "SAR" to "&#65020;",
    "RSD" to "&#1044;&#1080;&#1085;&#46;",
    "SCR" to "&#8360;",
    "SGD" to "&#36;",
    "SBD" to "&#36;",
    "SOS" to "&#83;",
    "ZAR" to "&#82;",
    "LKR" to "&#8360;",
    "SEK" to "&#107;&#114;",
    "CHF" to "&#67;&#72;&#70;",
    "SRD" to "&#36;",
    "SYP" to "&#163;",
    "TWD" to "&#78;&#84;&#36;",
    "THB" to "&#3647;",
    "TTD" to "&#84;&#84;&#36;",
    "TRY" to "&#8378;",
    "TRL" to "&#8356;",
    "TVD" to "&#36;",
    "UAH" to "&#8372;",
    "GBP" to "&#163;",
    "USD" to "&#36;",
    "UYU" to "&#36;&#85;",
    "UZS" to "&#1083;&#1074;",
    "VEF" to "&#66;&#115;",
    "VND" to "&#8363;",
    "YER" to "&#65020;",
    "ZWD" to "&#90;&#36;"
)