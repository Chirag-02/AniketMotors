package com.aniketassociates.aniketmotor.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.aniketassociates.R
import com.aniketassociates.aniketmotor.AniketMotorsHomeActivity
import com.aniketassociates.aniketmotor.register.AniketMotorsRegistrationActivity
import com.aniketassociates.databinding.ActivityAniketMotorsLoginBinding
import com.aniketassociates.util.Constant
import com.aniketassociates.util.EmplocPreferences
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class AniketMotorsLoginActivity : DaggerAppCompatActivity() {

    var loginText = false
    var passText = false
    var binding: ActivityAniketMotorsLoginBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val aniketMotorsLoginViewModel: AniketMotorsLoginViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var empLocPreferences: EmplocPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAniketMotorsLoginBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)
        toolbarFunctionality()
        binding?.loginButton?.setOnClickListener {
            checkEmptyFields()
        }
        binding?.registerButton?.setOnClickListener {
            startActivity(Intent(this, AniketMotorsRegistrationActivity::class.java))
        }
    }

    private fun toolbarFunctionality() {
        side_menu_button?.visibility = View.GONE
        title_text_toolbar?.text = getString(R.string.app_name)
    }

    private fun checkEmptyFields() {
        if (binding?.loginUserName?.text?.toString()
                .isNullOrEmpty() && binding?.loginUserName?.text?.length != 10 &&
            binding?.loginPasswordTexts?.text?.toString().isNullOrEmpty()
        ) {
            Toast.makeText(this, "Please fill All the details", Toast.LENGTH_SHORT).show()
        } else {
            callLoginAPI(
                binding?.loginUserName?.text?.toString()!!,
                binding?.loginPasswordTexts?.text?.toString()!!
            )
        }
    }

    private fun callLoginAPI(username: String, password: String) {
        aniketMotorsLoginViewModel.loginAniketMotors(
            username,
            password,
            this,
            supportFragmentManager
        )
        aniketMotorsLoginViewModel.success.observe(this, {
            empLocPreferences.getInstance()?.putString(Constant.ID, it?.data?.id)
            empLocPreferences.getInstance()?.putString(Constant.USERNAME, it?.data?.username)
            empLocPreferences.getInstance()?.putString(Constant.MOBILE, it?.data?.phoneno)
            startActivity(Intent(this, AniketMotorsHomeActivity::class.java))
            finish()
        })
        aniketMotorsLoginViewModel.error.observe(this, {
            println("ERROR $it")
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}