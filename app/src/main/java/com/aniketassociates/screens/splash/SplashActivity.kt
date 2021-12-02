package com.aniketassociates.screens.splash

import android.content.Intent
import android.os.Bundle
import com.aniketassociates.aniketmotor.AniketMotorsHomeActivity
import com.aniketassociates.aniketmotor.login.AniketMotorsLoginActivity
import com.aniketassociates.di.scoped.ActivityScoped
import com.aniketassociates.util.Constant
import com.aniketassociates.util.EmplocPreferences
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