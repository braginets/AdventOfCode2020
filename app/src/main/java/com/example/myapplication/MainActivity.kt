package com.example.myapplication

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
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


    private fun getRow(data: String): Int {
        val rowData = data.replace('F', '0').replace('B', '1')
        val decoded = Integer.parseInt(rowData, 2);
        return decoded.toInt()
    }

    private fun getColumn(data: String): Int {
        val columnData = data.replace('R', '1').replace('L', '0')
        val decoded = Integer.parseInt(columnData, 2);
        return decoded.toInt()

    }
    private fun start() {

        Log.d("AoC", "Start");
        val data = readFile()

        val ids = data.map {
                val row = getRow(it.dropLast(3))
                val column = getColumn(it.takeLast(3))
                row * 8 + column
            }
            .sorted()

        var seatID = ids.first()
        var i: Int = 0

        while (ids[i] == (i + seatID)) { i++ }

        Log.d("AoC - result", "${i + seatID}");
    }
}


