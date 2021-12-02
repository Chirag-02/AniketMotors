package com.aniketassociates.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aniketassociates.R
import com.aniketassociates.databinding.DialogOptionBinding


class OptionDialog(
    context: Context,
    @DrawableRes private val illustrationRes: Int = -1,
    @StringRes private val title: Int,
    private val subtitle: String,
    @StringRes private val positiveBtnText: Int,
    @StringRes private val negativeBtnText: Int,
    private val positiveClick: (() -> Unit)? = null,
    private val negativeClick: (() -> Unit)? = null
) : Dialog(context, R.style.AppTheme_Dialog) {
    private var binding: DialogOptionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DialogOptionBinding.inflate(layoutInflater, null, false)
        setContentView(binding?.root!!)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        if (illustrationRes != -1) {
//            binding.illustration.visibility = View.VISIBLE
//            binding.illustration.setImageResource(illustrationRes)
//        } else {
//            binding.illustration.visibility = View.GONE
//        }
//        binding.illustration.setImageResource(R.drawable.logo)
        if (positiveBtnText == R.string.ok) {
            binding?.negativeBtn?.visibility = View.GONE
        } else {
            binding?.negativeBtn?.visibility = View.VISIBLE
        }


        binding?.title?.text = context.getString(title)
        binding?.subtitle?.text = subtitle
        binding?.positiveBtn?.text = context.getString(positiveBtnText)
        binding?.negativeBtn?.text = context.getString(negativeBtnText)

        binding?.positiveBtn?.setOnClickListener {
            positiveClick?.invoke()
            dismiss()
            binding = null
        }
        binding?.negativeBtn?.setOnClickListener {
            negativeClick?.invoke()
            dismiss()
            binding = null
        }
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding = null
    }

}