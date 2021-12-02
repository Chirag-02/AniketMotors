package com.aniketassociates.screens.splash

import androidx.lifecycle.ViewModel
import com.aniketassociates.networking.Repositories

import javax.inject.Inject

class SplashViewModel @Inject constructor(private val repositories: Repositories) : ViewModel() {

//    val success = MutableLiveData<SplashResponse>()
//    val error = MutableLiveData<String>()
//
//    fun getSplashText(av: String, pt: String, did: String, city: String) {
//        viewModelScope.launch {
//            when(val splashResponse = repositories.getSplashText(av = av, pt = pt,did = did,city = city)){
//                is Result.Success ->{
//                    success.postValue(splashResponse.data)
//                    println("text-->"+splashResponse.data)
//                }
//                is Result.Error->{
//                    error.postValue(splashResponse.message)
//
//                }
//            }
//        }
//
//    }



}