package com.aniketassociates.aniketmotor.login

import android.app.Activity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aniketassociates.R
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsLoginResponse
import com.aniketassociates.networking.Repositories
import kotlinx.coroutines.launch
import com.aniketassociates.networking.Result
import com.aniketassociates.util.LoaderDialog
import com.aniketassociates.util.OptionDialog

import javax.inject.Inject

class AniketMotorsLoginViewModel @Inject constructor(private val repositories: Repositories) :
    ViewModel() {

    val success = MutableLiveData<AniketMotorsLoginResponse?>()
    val error = MutableLiveData<String>()

    fun loginAniketMotors(
        userName: String, passcode: String, activity: Activity,
        fragmentManager: FragmentManager
    ) {
        val loader = LoaderDialog(R.string.pleasewait)
        loader.show(fragmentManager, null)
        viewModelScope.launch {
            when (val splashResponse = repositories.loginAniketMotors(userName, passcode)) {
                is Result.Success -> {
                    loader.onDestroyView()

                    if (splashResponse.data.success) {
                        success.postValue(splashResponse.data)
                    } else {
                        val dialog = OptionDialog(
                            activity,
                            R.drawable.bottom_tab_account,
                            R.string.app_name,
                            splashResponse.data.message,
                            positiveBtnText = R.string.ok,
                            negativeBtnText = R.string.blank,
                            positiveClick = {

                            },
                            negativeClick = {

                            })
                        dialog.show()
                    }
                }

                is Result.Error -> {
                    loader.onDestroyView()
                    error.postValue(splashResponse.message!!)
                    val dialog = OptionDialog(
                        activity,
                        R.drawable.bottom_tab_account,
                        R.string.app_name,
                        splashResponse.message,
                        positiveBtnText = R.string.ok,
                        negativeBtnText = R.string.blank,
                        positiveClick = {

                        },
                        negativeClick = {

                        })
                    dialog.show()
                }
            }
        }

    }


}