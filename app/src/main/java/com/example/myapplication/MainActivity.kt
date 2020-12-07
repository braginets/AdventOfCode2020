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
        var bagsStore = HashMap<String, MutableList<Bag>>()

         data.forEach {

             // Learn finally regular expressions, Eugene!!!!
            val rule = it.split(" bags contain no other bags.", " bags contain ", " bag, ", " bags, ", " bag.", " bags.").dropLast(1)
            val outer = rule.first()
            rule.drop(1).forEach {
                val components = it.split(" ")
                val amount = components.first().toInt()
                val bagColor = components.drop(1).joinToString(" ")
                val bag = Bag(bagColor, amount)

                val bags: List<Bag> = bagsStore[outer] ?: listOf()
                bagsStore[outer] = (bags + listOf(bag)).toMutableList()
            }

        }

        Log.d("AoC - data", "${bagsStore}");

        return  findBags("shiny gold",1, bagsStore).count() - 1
    }


    fun findBags(bag: String, amount: Int, data: HashMap<String, MutableList<Bag>>): List<String> {

        //how do you multiply elements in collections properly in Kotlin?
        val children = data[bag] ?: return Collections.nCopies(amount, bag)

        val chain = children.map {
                findBags(it.color, it.amount, data)
            }.flatten()

        return Collections.nCopies(amount, bag) + Collections.nCopies(amount, chain).flatten()
        }

    private fun start() {

        val data = readFile()
    }
}


