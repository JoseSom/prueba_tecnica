package com.jsom.creditcard.core.util

fun String.capitalizeFirstLetter() = if (isNotEmpty()) {
    substring(0, 1).uppercase() + substring(1).lowercase()
} else { this }