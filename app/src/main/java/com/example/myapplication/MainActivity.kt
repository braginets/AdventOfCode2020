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

//        byr (Birth Year) - four digits; at least 1920 and at most 2002.
        val byrIndx = data.indexOf("byr")
        val byrIsValid = ((byrIndx >= 0) && (data[byrIndx + 1].count() == 4) && (data[byrIndx + 1].toInt() >= 1920) && (data[byrIndx + 1].toInt() <= 2002))

//        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        val iyrIndx = data.indexOf("iyr")
        val iyrIsValid = ((iyrIndx >= 0) && (data[iyrIndx + 1].count() == 4) && (data[iyrIndx + 1].toInt() >= 2010) && (data[iyrIndx + 1].toInt() <= 2020))

//        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        val eyrIndx = data.indexOf("eyr")
        val eyrIsValid = ((eyrIndx >= 0) && (data[eyrIndx + 1].count() == 4) && (data[eyrIndx + 1].toInt() >= 2020) && (data[eyrIndx + 1].toInt() <= 2030))

//        hgt (Height) - a number followed by either cm or in:
//        If cm, the number must be at least 150 and at most 193.
//        If in, the number must be at least 59 and at most 76.
        val hgtIndx = data.indexOf("hgt")
        var hgtIsValid = false
        if (hgtIndx >= 0) {
            val hgtVal = data[hgtIndx + 1]
            val hgtValInt = hgtVal.replace("[^\\d.]".toRegex(), "").toInt()
            if ((hgtVal.indexOf("cm") >= 0) && (hgtValInt >= 150) && (hgtValInt <= 193)) {
              hgtIsValid = true
            } else if ((hgtVal.indexOf("in") >= 0) && (hgtValInt >= 59) && (hgtValInt <= 76)) {
                hgtIsValid = true
            }
        }

//        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        val hclIndx = data.indexOf("hcl")
        var hclIsValid = ((hclIndx >= 0) && (data[hclIndx + 1].length == 7) && ("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$".toRegex().containsMatchIn(data[hclIndx + 1])))

//        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        val eclIndx = data.indexOf("ecl")
        var eclIsValid = false
        if (eclIndx >= 0) {

            val colors = listOf(
                "amb",
                "blu",
                "brn",
                "gry",
                "grn",
                "hzl",
                "oth"
            )

            val rx = Regex("\\b(?:${colors.joinToString(separator="|")})\\b")
            eclIsValid = rx.containsMatchIn(data[eclIndx + 1])

        }

//        pid (Passport ID) - a nine-digit number, including leading zeroes.
        val pidIndx = data.indexOf("pid")
        var pidIsValid = ((pidIndx >= 0) && (data[pidIndx + 1].length == 9))

        Log.d("AoC - result", "byr:${byrIsValid}, iyr:${iyrIsValid}, eyr:${eyrIsValid}, hgt:${hgtIsValid}, hcl:${hclIsValid}, ecl:${eclIsValid}, pid:${pidIsValid}");
        return byrIsValid && iyrIsValid && eyrIsValid && hgtIsValid && hclIsValid && eclIsValid && pidIsValid
    }

    private fun start() {

        Log.d("AoC", "Start");
        val data = readFile()

        var validPassports: Int = 0
        for (passportData in data) {
            val passport = passportData.split(" ", ":","\n")
            Log.d("AoC - pass", passport.toString());
            if (validatePassport(passport.toTypedArray())) { validPassports += 1 }
        }

        Log.d("AoC - result", validPassports.toString());
    }
}


