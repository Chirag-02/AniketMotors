package com.emploc.screens.loginmodule.devicepin

import android.content.Intent
import android.os.Bundle
import com.emploc.R
import com.emploc.databinding.ActivityDevicePinBinding
import com.emploc.screens.home.HomeActivity
import dagger.android.support.DaggerAppCompatActivity

class DevicePinActivity : DaggerAppCompatActivity() {
    var binding : ActivityDevicePinBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevicePinBinding.inflate(layoutInflater,null,false)
        val view = binding?.root
        setContentView(view)
        binding?.gotoHome?.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }


}