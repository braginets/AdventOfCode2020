package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.core.text.isDigitsOnly

class Day10(context: Context) {
    val context = context

    fun readFile(): Array<String> {
        val inputString = context.assets.open("day13.data").bufferedReader().use { it.readText() }
        return inputString.split("\n").toTypedArray()
    }

    fun solveProblem1(data: List<Int>): Int {
        val sortedAdapters = data.sorted()
        val maxAdapter = sortedAdapters.max()!!

        var adapter = 0
        var diffs = mutableListOf<Int>()
        while (adapter < maxAdapter) {
            val newAdapter = sortedAdapters.first {
                val validation= (it in adapter+1..adapter+3)
                validation
            }
            diffs.add(newAdapter - adapter)
            adapter = newAdapter
            Log.d("AoC", "adapter: $adapter")
        }
        var diffsMap = diffs.groupingBy { it }.eachCount()
        Log.d("AoC", "diffs: ${diffsMap}")

        return diffsMap[1]!! * (diffsMap[3]!! + 1)
    }


    fun solveProblem2(data: List<Int>): Long {
        val sortedAdapters = data.sorted()

        val paths = mutableMapOf<Int, Long>()

        //initialize connectors
        paths[1] = 1
        paths[2] = 1

        for (index in sortedAdapters) {
            val sum = (paths[index - 3] ?: 0) + (paths[index - 2] ?: 0) + (paths[index - 1] ?: 0) + (paths[index] ?: 0)
            paths[index] = sum
        }
        return paths[sortedAdapters.last()]!!

    }

}