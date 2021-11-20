package com.emploc.networking


import com.emploc.model.aniketMotorsModel.AniketMotorsListResponse
import com.emploc.model.aniketMotorsModel.AniketMotorsLoginResponse
import com.emploc.model.aniketMotorsModel.AniketMotorsRegistrationResponse
import com.emploc.util.EmplocPreferences
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class Repositories @Inject constructor(private val api: EmplocDataServices) : SafeApiRequest() {

    @Inject
    lateinit var kabadiplusPreferences: EmplocPreferences

    suspend fun loginAniketMotors(
        userName: String,
        passcode: String
    ): Result<AniketMotorsLoginResponse> {
        return apiRequest { api.loginAniketMotors(userName, passcode) }
    }

    suspend fun getCarsList(): Result<AniketMotorsListResponse> {
        return apiRequest { api.getCarsList() }
    }

    suspend fun registrationAPI(fileBody: RequestBody): Result<AniketMotorsRegistrationResponse> {
        return apiRequest {
            api.postMethod(fileBody)
        }
    }
}