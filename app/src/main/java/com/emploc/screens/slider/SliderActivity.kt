package com.emploc.screens.slider


import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.emploc.R
import com.emploc.databinding.ActivitySliderBinding
import com.emploc.screens.donthaveaccount.DontHaveAccountActivity
import com.emploc.screens.home.HomeActivity
import com.emploc.screens.loginmodule.signin.SignInActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity

class SliderActivity : DaggerAppCompatActivity() {
    var binding : ActivitySliderBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySliderBinding.inflate(layoutInflater,null,false)
        val view= binding?.root
        setContentView(view)

        val viewPager2: ViewPager2 = findViewById(R.id.viewPager)


        val list: MutableList<String> = ArrayList()
        list.add("First Screen")
        list.add("Second Screen")
        list.add("Third Screen")
        val tabLayout = findViewById<TabLayout>(R.id.tabs)

        viewPager2.adapter = ViewPagerAdapter(this, list)
        TabLayoutMediator(tabLayout, viewPager2) { _, _ ->
            //Some implementation...
        }.attach()


        binding?.textView?.setOnClickListener {
            startActivity(Intent(this,DontHaveAccountActivity::class.java))
            finish()
        }
        binding?.textView2?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }

    }
}