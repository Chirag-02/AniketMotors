package com.aniketassociates.aniketmotor.register

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aniketassociates.R
import com.aniketassociates.aniketmotor.login.AniketMotorsLoginActivity
import com.aniketassociates.databinding.ActivityAniketMotorsRegistrationBinding
import com.aniketassociates.util.EmplocPreferences
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import javax.inject.Inject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class AniketMotorsRegistrationActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val aniketMotorsRegistrationViewModel: AniketMotorsRegistrationViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var empLocPreferences: EmplocPreferences

    private var tmpFile: File? = null


    var binding: ActivityAniketMotorsRegistrationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAniketMotorsRegistrationBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)
        toolbarFunctionality()

        binding?.loginButton?.setOnClickListener {
            checkEmptyFields()
        }

        binding?.imageView26?.setOnClickListener {
            pickImage.launch("image/*")
        }

    }

    private fun toolbarFunctionality() {
        side_menu_button?.setOnClickListener {
            startActivity(Intent(this, AniketMotorsLoginActivity::class.java))
            finish()
        }
        title_text_toolbar?.text = getString(R.string.app_name)
    }


    private fun checkEmptyFields() {
        if (binding?.loginUserName?.text?.toString().isNullOrEmpty() &&
            binding?.loginPasswordTexts?.text?.toString().isNullOrEmpty() &&
            binding?.aadharText?.text?.toString().isNullOrEmpty() &&
            binding?.phoneNumberText?.text?.toString().isNullOrEmpty() &&
            binding?.parentIDText?.text?.toString().isNullOrEmpty() &&
            binding?.stateText?.text?.toString().isNullOrEmpty() &&
            binding?.cityText?.text?.toString().isNullOrEmpty()

        ) {
            Toast.makeText(this, "Please fill All the details", Toast.LENGTH_SHORT).show()
        } else if (tmpFile == null) {
            Toast.makeText(this, "Please add aadhar card image", Toast.LENGTH_SHORT).show()

        } else {
            uploadRequest()
        }
    }


    private fun copyToTempFile(input: Uri): Boolean {
        var inputStream: InputStream? = null
        var os: OutputStream? = null

        try {
            tmpFile = File(filesDir, "temp.png")
            inputStream = contentResolver.openInputStream(input) ?: return false
            os = FileOutputStream(tmpFile)

            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                os.write(buffer, 0, length)
            }
            return true
        } catch (e: Exception) {
            inputStream?.close()
            os?.close()
            e.printStackTrace()
            return false
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding?.imageView26?.setImageURI(it)
        if (!copyToTempFile(it)) {
            Toast.makeText(this, "Failed to create temp file", Toast.LENGTH_SHORT).show()
            return@registerForActivityResult
        }
    }


    private fun uploadRequest() {
        lifecycleScope.launch(IO) {
            val file = tmpFile ?: return@launch
            val body = file.asRequestBody("image/*".toMediaTypeOrNull())
            val uploadPart = MultipartBody.Part.createFormData("fileuploaded", file.name, body)


            val requestBody: RequestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(uploadPart)
                .addFormDataPart("username", binding?.loginUserName?.text?.toString()!!)
                .addFormDataPart("passcode", binding?.loginPasswordTexts?.text?.toString()!!)
                .addFormDataPart("phoneno", binding?.phoneNumberText?.text?.toString()!!)
                .addFormDataPart("adharcardno", binding?.aadharText?.text?.toString()!!)
                .addFormDataPart("parentid", binding?.parentIDText?.text?.toString()!!)
                .addFormDataPart("state", binding?.stateText?.text?.toString()!!)
                .addFormDataPart("district", binding?.cityText?.text?.toString()!!)
                .build()

            aniketMotorsRegistrationViewModel.uploadPostMethod(
                requestBody,
                this@AniketMotorsRegistrationActivity,
                supportFragmentManager
            )
        }

        aniketMotorsRegistrationViewModel.error.observe(this@AniketMotorsRegistrationActivity, {
            println("ERROR  $it")
        })
    }
}