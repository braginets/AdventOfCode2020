package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.core.text.isDigitsOnly

class Day08(context: Context) {
    val context = context

    fun readFile(): Array<String> {
        val inputString = context.assets.open("day13.data").bufferedReader().use { it.readText() }
        return inputString.split("\n").toTypedArray()
    }
}