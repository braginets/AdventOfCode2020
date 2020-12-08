package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

    fun bagParser(data: List<String>): Int {

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

    data class Instruction(val operation: String, val argument: String)

    private fun runCode(code: List<Instruction>): Int {

        val c: List<Int> = code.withIndex().filter { it.value.operation == "jmp" || it.value.operation == "nop" }.map { it.index }
        var result: Int = 0
        for (it in c) {

            var mutableCode = code.toMutableList()

            val inToReplace = mutableCode[it]

            if (inToReplace.operation == "jmp") {
                mutableCode[it] = Instruction("nop", inToReplace.argument)
            } else if (inToReplace.operation == "nop") {
                mutableCode[it] = Instruction("jmp", inToReplace.argument)
            }

            var accumulator: Int = 0
            var offset: Int = 0
            var offsets = mutableSetOf<Int>()
            var solved = true

            while (offset != mutableCode.count()) {

                val instruction = mutableCode[offset]

                if (offsets.contains(offset)) {
                    solved = false
                    break
                }

                offsets.add(offset)
                if (instruction.operation == "acc") {
                    accumulator += instruction.argument.toInt()
                    offset += 1
                } else if (instruction.operation == "jmp") {
                    offset += instruction.argument.toInt()
                } else if (instruction.operation == "nop") {
                    offset += 1
                }

            }

            if (solved) {
                result = accumulator
                break
            }
        }

        return result
    }


    private fun problem1(data: List<String>) {
        Log.d("AoC - data", "${bagParser(data)}");
    }

    private fun problem2(data: List<String>) {
        val code = data.map {
            val instr = it.split(" ")
            Instruction(instr[0], instr[1])
        }

        runCode(code)
    }

    private fun start() {

        val data = readFile().toList()

//        problem1(data)
        problem2(data)
    }
}


