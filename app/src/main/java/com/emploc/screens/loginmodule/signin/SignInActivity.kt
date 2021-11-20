package com.emploc.screens.loginmodule.signin

import android.content.Intent
import android.os.Bundle
import com.emploc.databinding.ActivitySignInBinding
import com.emploc.screens.loginmodule.verification.VerificationActivity
import dagger.android.support.DaggerAppCompatActivity

class SignInActivity : DaggerAppCompatActivity() {
    var binding: ActivitySignInBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)

        binding?.countryCodePicker?.ccpDialogShowFlag = true
        binding?.countryCodePicker?.isSearchAllowed = true
        binding?.countryCodePicker?.setShowPhoneCode(true)
        binding?.countryCodePicker?.showNameCode(true)
        binding?.countryCodePicker?.showArrow(true)

        binding?.gotoVerification?.setOnClickListener {
            startActivity(Intent(this,VerificationActivity::class.java))
        }

    }

    private fun loginApiCall(){
//        binding?.userMobileNumber?.text
    }


}