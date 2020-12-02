package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    var data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculate()
    }

    fun readFile(): Array<Int> {
        val inputString = application.assets.open("data.txt").bufferedReader().use { it.readText() }
        val list: List<String> = inputString.split("\n")
        return list.map { it.toInt() }.toTypedArray()
    }


    private fun calculate() {

        val data = readFile()


        for (i in data) {
            for (j in data) {
                for (k in data) {
                    if (i + j + k == 2020) {
                        println(i * j * k)
                    }
                }
            }
        }


//        println(.contentToString())



    }
}
