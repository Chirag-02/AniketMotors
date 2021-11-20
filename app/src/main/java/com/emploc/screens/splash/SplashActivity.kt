package com.emploc.screens.splash

import android.content.Intent
import android.os.Bundle
import com.emploc.R
import com.emploc.aniketmotor.AniketMotorsHomeActivity
import com.emploc.aniketmotor.login.AniketMotorsLoginActivity
import com.emploc.di.scoped.ActivityScoped
import com.emploc.screens.slider.SliderActivity
import com.emploc.util.Constant
import com.emploc.util.EmplocPreferences
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

@ActivityScoped
class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var empLocPreferences: EmplocPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (empLocPreferences.getString(Constant.ID)?.isNotEmpty() == true) {
            startActivity(Intent(this, AniketMotorsHomeActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, AniketMotorsLoginActivity::class.java))
            finish()
        }

    }
}