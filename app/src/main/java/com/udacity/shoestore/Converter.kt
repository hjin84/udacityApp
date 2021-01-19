package com.udacity.shoestore

import androidx.databinding.InverseMethod
object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double): String {
        // Converts long to String.
        return value.toString()
    }
    @JvmStatic
    fun stringToDouble(value: String): Double {
        // Converts String to long.
        return value.toDoubleOrNull() ?: 0.0
    }
}