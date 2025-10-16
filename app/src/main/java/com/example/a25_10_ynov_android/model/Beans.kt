package com.example.a25_10_ynov_android.model

import java.util.Random


fun main() {
    var t = ThermometerBean(-20, 50, 0)


    val t2 = ThermometerBean.getCelsiusThermometer()
}

class RandomName() {
    private val list = arrayListOf("toto", "bob", "titi")
    private var oldValue = ""

    fun add(name: String?) = if (!name.isNullOrBlank() && name !in list)
        list.add(name)
    else false

    fun addAll(vararg names: String) {
        for (name in names) {
            add(name)
        }
    }

    fun next() = list.random()

    fun nextDiff2(): String {
        oldValue = list.filter { it != oldValue }.random()
        return oldValue
    }

    fun next2() = Pair(nextDiff(), nextDiff())

    fun nextDiff3() = list.filter { it != oldValue }.random().also { oldValue = it }

    fun nextDiff(): String {
        var newName = next()
        while (newName == oldValue) {
            newName = next()
        }

        oldValue = newName
        return newName
    }
}

class ThermometerBean(val min: Int, val max: Int, value: Int) {

    var value: Int = value.coerceIn(min, max)
        set(newValue) {
            field = if (newValue < min) min else if (newValue > max) max else newValue
        }


    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)
        fun getFahrenheitThermometer() = ThermometerBean(20, 120, 32)
    }
}

class PrintRandomIntBean(val max: Int) {
    val random: Random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }
}

class HouseBean(var color: String, width: Int, length: Int) {
    var area = width * length
}

data class CarBean(var marque: String = "", var model: String = "") {
    var color = ""

}

