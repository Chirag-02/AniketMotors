package com.emploc.screens.loginmodule.verification

import android.content.Intent
import android.os.Bundle
import com.emploc.R
import com.emploc.databinding.ActivityVerificationBinding
import com.emploc.screens.home.HomeActivity
import com.emploc.screens.loginmodule.devicepin.DevicePinActivity
import dagger.android.support.DaggerAppCompatActivity

class VerificationActivity : DaggerAppCompatActivity() {
    var binding: ActivityVerificationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater, null, false)
        val view= binding?.root
        setContentView(view)

        binding?.gotoHome?.setOnClickListener {
            startActivity(Intent(this,DevicePinActivity::class.java))
        }
    }
}

