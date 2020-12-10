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

        fun check(data: List<Long>, number: Long): Boolean {
            data.forEach() { i1 ->
                val res = data.find { i2 -> i1 + i2 == number && i1 != i2  }
                if (res != null) return true
            }
            return  false
        }


    fun solveProblem1(data: List<Long>, preamble: Int): Long {

        for (i in preamble until data.size) {
            if (!check(data.subList(i - preamble, i), data[i])) {
                return data[i]
            }
        }
        return -1
    }

    fun solveProblem2(data: List<Long>): Long {
        val target = solveProblem1(data, 25)
        var i = 0
        var i1 = 0
        var sum: Long = 0

        while (i < data.size) {
            if (sum == target && i + 1 < i1) {
                val range = data.subList(i, i1)
                return range.min()!! + range.max()!!
            } else if (sum < target && i1 < data.size) {
                sum += data[i1++]
            } else if (i < data.size) {
                sum -= data[i++]
            }
        }
        return -1
    }

    private fun start() {

        Log.d("AoC", "start")

        val data = readFile().toList().map { it.toLong() }
        val result1 = solveProblem1(data, 25)
        val result2 = solveProblem2(data)


        Log.d("AoC", "$result1")
        Log.d("AoC", "$result2")
    }
}


