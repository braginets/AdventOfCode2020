package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import android.provider.Contacts
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.text.isDigitsOnly
import java.lang.Math.pow


class Day14(context: Context) {
    val context = context

    fun readFile(): Array<String> {
        val inputString = context.assets.open("day14.data").bufferedReader().use { it.readText() }
        return inputString.split("\nmask = ").toTypedArray()
    }


    fun toDecimal(binaryNumber : String) : Long {
        var sum = 0L
        binaryNumber.reversed().forEachIndexed {
                k, v -> sum += (v.toString().toLong() * pow(2.0, k.toDouble())).toLong()
        }
        return sum
    }

    fun toBinary(decimalNumber: Long, binaryString: String = "") : String {
        while (decimalNumber > 0) {
            val temp = "${binaryString}${decimalNumber%2}"
            return toBinary(decimalNumber/2, temp)
        }
        return binaryString.reversed()
    }

    fun applyBitmask(bitMask: String, number: Long): Long {

        val numberBits = toBinary(number).padStart(36, '0')

        val bitMaskArray = bitMask.toCharArray().map { if (it == 'X') { null}  else { it } }
        val updatedNumber = numberBits.mapIndexed { i, e ->
            bitMaskArray[i] ?: e
        }.joinToString ("")
        return toDecimal(updatedNumber)
    }

    fun solveProblem1() {

        val ops = readFile()
        Log.d("AoC", "ops: ${ops.toList()}")

        val memArray: MutableList<Pair<Long, Long>> = mutableListOf()
        ops.forEach { block ->
            val data = block.split("\n")
            val bitmask = data.first()// .replace("[^\\w]*mask[^\\w]*".toRegex(), "")
            memArray.addAll(data.drop(1).map {
                it.split("([^\\w]*mem[^\\w]*)|([^\\w]* = [^\\w]*)".toRegex())
            }.map { it[1].toLong() to applyBitmask(bitmask, it[2].toLong()) })
        }

        val sum = memArray.associate {
            it.first to it.second
        }.values.reduce { acc, i -> acc + i }

        Log.d("AoC", "Solution problem 1: ${sum}")

    }

    fun solveProblem2() {

//        Log.d("AoC", "Solution problem 2: ${time}")
    }

}