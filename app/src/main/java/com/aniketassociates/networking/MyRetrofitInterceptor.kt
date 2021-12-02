package com.aniketassociates.networking

import com.aniketassociates.util.Constant
import com.aniketassociates.util.EmplocPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

 class MyRetrofitInterceptor : Interceptor {
    @Inject
    lateinit var kabadiplusPreferences: EmplocPreferences
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val token = kabadiplusPreferences.getString(Constant.TOKEN)// get token logic
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", token.toString())
            .build()
        return chain.proceed(newRequest)
    }


}