package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.HashMap
import java.util.NavigableMap

const val bagName = "shiny gold"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    fun readFile(): Array<String> {
        val inputString = application.assets.open("data.txt").bufferedReader().use { it.readText() }
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
        Log.d("AoC", "sorted: ${sortedAdapters}")

        val paths = mutableMapOf<Int, Long>()

        //initialize paths
            paths[1] = 1
            paths[2] = 1
//            paths[3] = 1

        for (a in sortedAdapters) {
            val sum = (paths[a - 3] ?: 0) + (paths[a - 2] ?: 0) + (paths[a - 1] ?: 0) + (paths[a] ?: 0)
            paths[a] = sum
        }
        return paths[sortedAdapters.last()]!!

    }


    private fun start() {

        Log.d("AoC", "start")


        val data = readFile().toList().map { it.toInt() }

//        val e = rle(data)
//        val result1 = solveProblem1(data)
        val result2 = solveProblem2(data)

//        Log.d("AoC", "$result1")
        Log.d("AoC", "$result2")
    }
}


