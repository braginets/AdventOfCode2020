package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import android.provider.Contacts
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.text.isDigitsOnly


class Day15(context: Context) {
    val context = context

    fun readFile(): Array<String> {
        val inputString = context.assets.open("day14.data").bufferedReader().use { it.readText() }
        return inputString.split("\nmask = ").toTypedArray()
    }


    fun solveProblem1() {

//        Log.d("AoC", "Solution problem 1: ${}")

    }

    fun solveProblem2() {

//        Log.d("AoC", "Solution problem 2: ${time}")
    }

}