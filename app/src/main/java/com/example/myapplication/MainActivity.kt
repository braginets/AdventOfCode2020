package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

data class Policy(val pos1: Int, val pos2: Int, val letter: Char)
data class Record(val policy: Policy, val password: String) {
    fun valiatePass(): Boolean {
        val isPos1 = password.toCharArray()[policy.pos1 - 1] == policy.letter
        val isPos2 = password.toCharArray()[policy.pos2 - 1] == policy.letter
        val result = isPos1.xor(isPos2)
        return result
    }
}

class MainActivity : AppCompatActivity() {

    var data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculate()
    }

    fun readFile(): Array<String> {
        val inputString = application.assets.open("data.txt").bufferedReader().use { it.readText() }
        val list: List<String> = inputString.split("\n")
        return list.toTypedArray()
    }




    private fun calculate() {

        Log.d("AoC", "Start");

        val data = readFile()


        val records: List<Record> = data.map {
            val record = it.splitToSequence(" ", "-", ":").toList()
            val policy = Policy(record[0].toInt(), record[1].toInt(), record[2].single())
            Record(policy, record[4])
        }.filter {
            it.valiatePass() == true
        }



        Log.d("AoC", records.count().toString());



//        println(.contentToString())



    }
}


