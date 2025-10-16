package com.example.a25_10_ynov_android.exo

import com.example.a25_10_ynov_android.model.CarBean

class MyLiveData<T>(value : T) {
    var value = value
        set(newValue) {
            field = newValue
            action?.invoke(newValue)
        }

    var action : ((T)->Unit)? = null
        set(newAction) {
            field = newAction
            newAction?.invoke(value)
        }

}

fun main() {
    var toto = MyLiveData(CarBean("Seat", "leon"))

    toto.value.model = "Ibiza"
    toto.value = CarBean("Seat", "Ibiza")
    toto.value = toto.value.copy(model = "Ibiza")


    toto.action = {
        println(it)
    }

}

fun exo1() {

    //Déclaration
    val lower: (String) -> Unit = { text: String -> println(text.lowercase()) }
    val lower2 = { text: String -> println(text.lowercase()) }
    val lower3 : (String) -> Unit = { text -> println(text.lowercase()) }
    val lower4: (String) -> Unit = {
        println(it.lowercase())
    }

    val hour : (Int)->Int = { it/60}
    val max = { a:Int, b : Int -> Math.max(a,b) }
    val reverse = { a:String -> a.reversed() }

    var minToMinHour : ((Int?)-> Pair<Int,Int>?)? = { if(it==null) null else Pair(it/60, it%60)

    }

    println(minToMinHour?.invoke(123))
    println(minToMinHour?.invoke(null))
    minToMinHour = null

    //Appel
    lower("Coucou")
    println(hour(122))
}

data class PersonBean(var name:String, var note:Int)


fun exo3(){

    val list = arrayListOf(PersonBean("toto", 16), PersonBean("Bobby", 20), PersonBean("Toto", 8), PersonBean("Charles", 14))

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >=10 }.joinToString("\n") { "-${it.name} : ${it.note}"})

    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    val lambdaNomToto = { it: PersonBean -> it.name.equals("toto", true) }
    println(list.count(lambdaNomToto))
    //println(list.count{ lambdaNomToto(it) })

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    println(list.count { lambdaNomToto(it) && it.note >= 10 })

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val moyenne = list.map { it.note }.average()
    println(list.count { lambdaNomToto(it) && it.note >= moyenne })

    println("\n\nAfficher les noms sans doublon par ordre alphabétique")
    println(list.map { it.name }.distinctBy { it }.sortedBy { it.lowercase() })

    println("\n\nAjouter un point à ceux n’ayant pas la moyenne (<10)")
    list.filter { it.note < 10 }.forEach { it.note++ }

    println("\n\nAjouter un point à tous les Toto")
    list.filter(lambdaNomToto).forEach { it.note++ }

    println("\n\nRetirer de la liste ceux ayant la note la plus petite")
    val minNote = list.minOfOrNull { it.note }
    list.removeIf { it.note == minNote }

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
    print(list.filter { it.note >= 10 }.map { it.name }.sortedBy { it })
    //V2
    list.filter { it.note >= 10 }.map { it.name }.sortedBy { it }.forEach(::println)

    println("\n\nDupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient")
    val list2 = list
    val list3 = ArrayList(list)
    val copyList = list.map{it.copy()}

    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")
    val text = list.groupBy { it.note } //On groupe par note
        .entries //la HashMap sous forme de List<Pair<Key , List<PersonBean>>>
        .sortedBy { it.key } //On trie en fonction de la note
        .joinToString("\n") {//Pour chaque note
            //On met la note en préfixe et on affiche chaque nom
            it.value.joinToString(", ", "${it.key} : ") {it.name}
        }
    //on affiche
    println(text)
}