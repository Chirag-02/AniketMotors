package com.emploc.screens.donthaveaccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.emploc.R
import com.emploc.databinding.ActivityDontHaveAccountBinding
import com.emploc.screens.choosesignupmode.ChooseSignupModeActivity
import com.emploc.screens.slider.SliderActivity
import dagger.android.support.DaggerAppCompatActivity

class DontHaveAccountActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding: ActivityDontHaveAccountBinding? = null
        super.onCreate(savedInstanceState)
        binding = ActivityDontHaveAccountBinding.inflate(layoutInflater, null, false)
        val view = binding.root
        setContentView(view)

        binding.getstarted.setOnClickListener {
            startActivity(Intent(this, ChooseSignupModeActivity::class.java))
            finish()
        }
        binding.whatsApp.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }
        binding.gmail.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, SliderActivity::class.java))
        finish()
    }
}