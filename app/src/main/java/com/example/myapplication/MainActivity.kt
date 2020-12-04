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
        val list: List<String> = inputString.split("\n\n")
        return list.toTypedArray()
    }

    data class Slope(val x: Int, val y: Int)

    private fun validatePassport(data: Array<String>): Boolean {

        if (!data.contains("byr")) return false
        if (!data.contains("iyr")) return false
        if (!data.contains("eyr")) return false
        if (!data.contains("hgt")) return false
        if (!data.contains("hcl")) return false
        if (!data.contains("ecl")) return false
        if (!data.contains("pid")) return false
        return true
    }

    private fun start() {

        Log.d("AoC", "Start");
        val data = readFile()

        var validPassports: Int = 0
        for (passportData in data) {
            val passport = passportData.split(" ", ":","\n").toTypedArray()
            Log.d("AoC - pass", passport.toString());
            if (validatePassport(passport)) { validPassports += 1 }
        }

        Log.d("AoC - result", validPassports.toString());
    }
}


