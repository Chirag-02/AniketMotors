package com.emploc.screens.editprofile


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.emploc.R
import com.emploc.databinding.ActivityEditProfileBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*

class EditProfileActivity : DaggerAppCompatActivity() {
    private var genderText: String? = null

    var binding: ActivityEditProfileBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)
        toolbarFunctionality()
        genderSpinner()
    }

    private fun toolbarFunctionality() {
        title_text_toolbar.text = "Edit Profile"
        side_menu_button.setOnClickListener {
            onBackPressed()
        }
    }

    private fun genderSpinner() {
        val items = listOf("Male", "Female")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        (binding?.genderlayout?.editText as? AutoCompleteTextView)?.apply {
            setOnDismissListener {
                binding?.genderlayout?.clearFocus()
            }
        }?.setAdapter(adapter)

        binding?.itemValue?.setOnItemClickListener { adapterView, view, i, l ->
            genderText = binding?.itemValue?.text!!.toString()
            println("SPINNEER : $genderText")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}