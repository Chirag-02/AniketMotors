package com.emploc.screens.choosesignupmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emploc.databinding.ActivityChooseSignupModeBinding
import com.emploc.screens.signupform.SignUpActivity
import com.emploc.screens.slider.SliderActivity
import dagger.android.support.DaggerAppCompatActivity

class ChooseSignupModeActivity : DaggerAppCompatActivity() {
    var binding: ActivityChooseSignupModeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseSignupModeBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)

        binding?.signIntoLystloc?.setOnClickListener {

        }
        binding?.setupANewCompany?.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, SliderActivity::class.java))
        finish()
    }
}