package com.emploc.aniketmotor.register

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emploc.R
import com.emploc.aniketmotor.login.AniketMotorsLoginActivity
import com.emploc.model.aniketMotorsModel.AniketMotorsRegistrationResponse
import com.emploc.networking.Repositories
import kotlinx.coroutines.launch
import com.emploc.networking.Result
import com.emploc.util.LoaderDialog
import com.emploc.util.OptionDialog
import okhttp3.MultipartBody
import okhttp3.RequestBody

import javax.inject.Inject

class AniketMotorsRegistrationViewModel @Inject constructor(private val repositories: Repositories) :
    ViewModel() {

    val success = MutableLiveData<AniketMotorsRegistrationResponse?>()
    val error = MutableLiveData<String>()

    suspend fun uploadPostMethod(
        bodyIs: RequestBody, activity: Activity,
        fragmentManager: FragmentManager
    ) {
        val loader = LoaderDialog(R.string.pleasewait)
        loader.show(fragmentManager, null)
        viewModelScope.launch {
            when (val splashResponse = repositories.registrationAPI(
                bodyIs
            )) {
                is Result.Success -> {
                    loader.onDestroyView()

                    if (splashResponse.data.success) {
                        val dialog = OptionDialog(
                            activity,
                            R.drawable.bottom_tab_account,
                            R.string.app_name,
                            splashResponse.data.message,
                            positiveBtnText = R.string.ok,
                            negativeBtnText = R.string.blank,
                            positiveClick = {
                                activity.startActivity(
                                    Intent(
                                        activity,
                                        AniketMotorsLoginActivity::class.java
                                    )
                                )
                                activity.finish()
                            },
                            negativeClick = {

                            })
                        dialog.show()
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