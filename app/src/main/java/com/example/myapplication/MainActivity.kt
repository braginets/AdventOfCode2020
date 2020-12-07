package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.time.temporal.TemporalAmount
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

    data class Bag(val color: String, val amount: Int)

    fun bagParser(data: Array<String>): Int {

        // key is the innner bag, value is list of all outer bags
        var reversedBags = HashMap<String, MutableList<Bag>>()

         data.forEach {

             // Learn finally regular expressions, Eugene!!!!
            val rule = it.split(" bags contain no other bags.", " bags contain ", " bag, ", " bags, ", " bag.", " bags.").dropLast(1)
            val outer = rule.first()
            rule.drop(1).forEach {
                val components = it.split(" ")
                val amount = components.first().toInt()
                val bagColor = components.drop(1).joinToString(" ")
                val bag = Bag(outer, amount)

                val bags: List<Bag> = reversedBags[bagColor] ?: listOf()
                reversedBags[bagColor] = (bags + listOf(bag)).toMutableList()
            }

        }

        return  findParents("shiny gold",reversedBags).toSet().count()
    }


    fun findParents(bag: String, data: HashMap<String, MutableList<Bag>>): List<String> {

        val parents = data[bag] ?: return listOf(bag)
        val chain = parents.map {
                listOf(it.color) + findParents(it.color, data)
            }.flatten().toList()
        return chain
        }

    private fun start() {

        Log.d("AoC", "Start");
        val data = readFile()

        Log.d("AoC", "${bagParser(data)}");


    }
}


