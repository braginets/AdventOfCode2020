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
        return inputString.split("\n\n").toTypedArray()
    }

    private fun start() {

        Log.d("AoC", "Start");
        val data = readFile()

        val answer = data
            .map { it.split("\n")
                .map { it.toSet() }
                }
            .map {
                var set = it.first().toMutableSet()
                for (a in it) {
                    set.retainAll(a)
                }
                set
            }
            .flatMap { it.asIterable() }
            .count()

        Log.d("AoC - result", "${answer}");
    }
}


