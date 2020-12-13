package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.util.Log


class Day13(context: Context) {
    val context = context

    fun readFile(context: Context): Array<String> {
        val inputString = context.assets.open("day13.data").bufferedReader().use { it.readText() }
        return inputString.split("\n").toTypedArray()
    }

    fun solveProblem1() {
        Log.d("AoC", "Solution problem 1: ")

    }

}