package com.aniketassociates.aniketmotor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsListResponse
import com.aniketassociates.networking.Repositories
import kotlinx.coroutines.launch
import com.aniketassociates.networking.Result

import javax.inject.Inject

class AniketMotorsHomeViewModel @Inject constructor(private val repositories: Repositories) : ViewModel() {

    val success = MutableLiveData<AniketMotorsListResponse>()
    val error = MutableLiveData<String>()

    fun getCarsData() {
        viewModelScope.launch {
            when(val splashResponse = repositories.getCarsList()){
                is Result.Success->{
                    success.postValue(splashResponse.data!!)
                    println("text-->"+splashResponse.data)
                }
                is Result.Error->{
                    error.postValue(splashResponse.message!!)

                }
            }
        }

    }



}