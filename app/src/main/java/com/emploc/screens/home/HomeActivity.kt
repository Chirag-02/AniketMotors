package com.emploc.screens.home

import android.os.Bundle
import com.emploc.R
import com.emploc.databinding.ActivityHomeBinding
import com.emploc.screens.bottomtab.account.MyAccountFragment
import com.emploc.screens.bottomtab.attendance.AttendanceFragment
import com.emploc.screens.bottomtab.tasks.TasksFragment
import com.emploc.screens.bottomtab.track.TrackFragment
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {
    var binding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)
        bottomNavigationFunctionality()
    }

    private fun bottomNavigationFunctionality() {
        val attendanceTab = AttendanceFragment()
        val tasksTab = TasksFragment()
        val trackTab = TrackFragment()
        val accountTab = MyAccountFragment()
        binding?.bottomNavigationView?.setOnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.attendance_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.home_activity_frag_container, attendanceTab).commit()

                }

                R.id.task_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.home_activity_frag_container, tasksTab).commit()
                }

                R.id.track_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.home_activity_frag_container, trackTab).commit()

                }

                R.id.account_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.home_activity_frag_container, accountTab).commit()

                }
            }
            true
        }
    }


}