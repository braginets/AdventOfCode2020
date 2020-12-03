package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    fun readFile(): Array<String> {
        val inputString = application.assets.open("data.txt").bufferedReader().use { it.readText() }
        val list: List<String> = inputString.split("\n")
        return list.toTypedArray()
    }

    data class Slope(val x: Int, val y: Int)

    private fun treeCounter(data: Array<String>, slope: Slope): Int {

        var x: Int = 0
        var y: Int = 0
        val lineLength = 31

        var treeCounter: Int = 0

        while (y < data.count()) {

            if (data[y].get(x) == '#') {
                treeCounter ++
            }

            x += slope.x
            y += slope.y

            if (x > (lineLength - 1)) {
                x -= lineLength
            }

        }

        return treeCounter
    }

    private fun start() {

        Log.d("AoC", "Start");
        val data = readFile()

        val slopes = arrayOf(Slope(1,1), Slope(3,1), Slope(5,1), Slope(7,1), Slope(1,2))

        var trees: Int = 1

        for (slope in slopes) {
            val treesForSlope = treeCounter(data, slope)
            Log.d("AoC - trees", treesForSlope.toString());
            trees *= treesForSlope
        }

        Log.d("AoC - result", trees.toString());
    }
}


