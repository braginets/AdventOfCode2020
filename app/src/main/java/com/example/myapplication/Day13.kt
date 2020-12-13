package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.core.text.isDigitsOnly


class Day13(context: Context) {
    val context = context

    fun readFile(): Array<String> {
        val inputString = context.assets.open("day13.data").bufferedReader().use { it.readText() }
        return inputString.split("\n").toTypedArray()
    }

    fun solveProblem1() {
        val data = readFile().toList()
        val time = data.first().toInt()
        val buses = data[1]
            .split(",")
            .filter { it.isDigitsOnly() }
            .map { it.toInt() }

        val nextBus = buses.sortedBy {
            it - time % it
        }.first()
        val toWait = nextBus - time % nextBus

        Log.d("AoC", "Solution problem 1: ${nextBus * toWait}")

    }

    fun solveProblem2() {
        val data = readFile().toList()
        val buses = data
            .last()
            .split(",")
            .map { it.toLongOrNull() ?: 1 }

        var increment = buses.first()
        var index = 1
        var time = increment

        while (index < buses.count()) {
            if ((time + index) % buses[index] == 0L) {
                increment *= buses[index]
                index += 1
            } else {
              time += increment
            }
        }
        Log.d("AoC", "Solution problem 2: ${time}")
    }

}