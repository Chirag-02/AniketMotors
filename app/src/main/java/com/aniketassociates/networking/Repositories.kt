package com.aniketassociates.networking


import com.aniketassociates.model.aniketMotorsModel.AniketMotorsListResponse
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsLoginResponse
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsRegistrationResponse
import com.aniketassociates.util.EmplocPreferences
import okhttp3.RequestBody
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