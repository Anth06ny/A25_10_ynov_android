package com.example.a25_10_ynov_android.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a25_10_ynov_android.model.KtorWeatherApi
import com.example.a25_10_ynov_android.model.WeatherBean
import kotlinx.coroutines.flow.MutableStateFlow

suspend fun main(){
    val viewModel = MainViewModel()
    viewModel.loadWeathers("Nice")
    //Affichage de la liste (qui doit être remplie) contenue dans la donnée observable
    println("List : ${viewModel.dataList.value}" )

    //Pour que le programme s'arrête, inutile sur Android
    KtorWeatherApi.close()
}

class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<WeatherBean>())

    suspend fun loadWeathers(cityName:String){
        //TODO récupérer des données et les mettre dans dataList
        dataList.value = KtorWeatherApi.loadWeathers(cityName)
    }
}