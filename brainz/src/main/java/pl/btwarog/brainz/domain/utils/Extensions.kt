package pl.btwarog.brainz.domain.utils

fun String?.orUnknown(): String = this ?: "-"