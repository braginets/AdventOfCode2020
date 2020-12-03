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

    private fun start() {

        Log.d("AoC", "Start");
        val data = readFile()
        var x: Int = 0
        var treeCounter: Int = 0

        for (d in data) {
            
            if (d.get(x) == '#') {
                treeCounter ++
            }

            x += 3
            if (x > 30) {
                x -= 31
            }

        }

        Log.d("AoC", treeCounter.toString());
    }
}


