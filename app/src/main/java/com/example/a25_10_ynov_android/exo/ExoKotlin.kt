package com.example.a25_10_ynov_android.exo

import com.example.a25_10_ynov_android.PRICE_BAGUETTE
import com.example.a25_10_ynov_android.PRICE_CROISSANT
import com.example.a25_10_ynov_android.PRICE_SANDWITCH
import kotlin.random.Random

var v2: String? = "coucou"

fun main() {
    println("hello")

    var v1 = "coucou"

    var v3: String? = null

    println(v1.uppercase())

    var v2temp = v2
    if(v2temp != null) {
        //Apple d'api

        println(v2temp.uppercase())
    }
    else {
        println(v2temp)
    }

    println(v2?.uppercase())





    println(v3?.uppercase())

    var v4: Int? = null
//Laisser le curseur de la souris sur Random pour qu'il vous propose de l'importer
//Choisir celui de Koltin
    if (Random.nextBoolean()) {
        v4 = Random.nextInt(10)
    }
    println(v4 ?: "Pas de valeur")

    var v5  = v3 + v3
    println(v5)

    println(boulangerie(1,2,3))
    println(boulangerie(nbSand = 2))  //boulangerie(0,0,2)

}

fun boulangerie(nbCroi:Int = 0, nbBag:Int =0, nbSand:Int =0 )
    = nbCroi * PRICE_CROISSANT + nbBag * PRICE_BAGUETTE + nbSand * PRICE_SANDWITCH













