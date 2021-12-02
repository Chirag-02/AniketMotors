package com.aniketassociates.networking


import com.aniketassociates.BuildConfig
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsListResponse
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsLoginResponse
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.IOException
import retrofit2.http.POST

import retrofit2.http.FormUrlEncoded

import com.aniketassociates.model.aniketMotorsModel.AniketMotorsRegistrationResponse
import okhttp3.*
import okhttp3.RequestBody

import retrofit2.http.Body


interface EmplocDataServices {

    @GET("reg_list.php")
    suspend fun getCarsList(
    ): retrofit2.Response<AniketMotorsListResponse>

    @FormUrlEncoded
    @POST("login.php")
    suspend fun loginAniketMotors(
        @Field("username") username: String?,
        @Field("passcode") passcode: String?
    ): retrofit2.Response<AniketMotorsLoginResponse>


    @POST("/reg.php")
    suspend fun postMethod(@Body requestBody: RequestBody): retrofit2.Response<AniketMotorsRegistrationResponse>

    companion object {
        operator fun invoke(string: String?): EmplocDataServices {
            val baseUrl = "http://app.aniketmotors.com/"
            val client = OkHttpClient.Builder()
            client.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .build()
                    return chain.proceed(newRequest)
                }
            })

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }

            return Retrofit.Builder()
                .client(client.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(EmplocDataServices::class.java)
        }
    }
}

