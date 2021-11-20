package com.emploc.screens.signupform

import android.content.Intent
import android.os.Bundle
import com.emploc.databinding.ActivitySignUpBinding
import com.emploc.screens.choosesignupmode.ChooseSignupModeActivity
import com.emploc.screens.slider.SliderActivity
import dagger.android.support.DaggerAppCompatActivity

class SignUpActivity : DaggerAppCompatActivity() {
    var binding: ActivitySignUpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)
        toolbarFunctionality()
    }
    private fun toolbarFunctionality(){
        binding?.tool?.titleTextToolbar?.text="Sign Up"

    }
    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, ChooseSignupModeActivity::class.java))
        finish()
    }
}