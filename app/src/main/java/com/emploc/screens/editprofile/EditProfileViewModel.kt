package com.emploc.screens.editprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emploc.networking.Repositories

import javax.inject.Inject

class EditProfileViewModel @Inject constructor(private val repositories: Repositories) : ViewModel() {

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