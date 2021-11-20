package com.emploc.screens.slider

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.ViewModel
import com.emploc.networking.Repositories
import javax.inject.Inject

class SliderViewModel @Inject constructor(private val repositories: Repositories) : ViewModel() {
    private val SPLASH_DISPLAY_LENGTH = 1000


    fun gotoNext(mContext: Activity) {
//            val mainIntent = Intent(mContext, MTLoginActivity::class.java)
//            mContext.startActivity(mainIntent)
//            mContext.finish()
    }

}