package com.emploc.screens.tasksinfo

import android.os.Bundle
import com.emploc.databinding.ActivityTasksInformationBinding
import dagger.android.support.DaggerAppCompatActivity

class TasksInformationActivity : DaggerAppCompatActivity() {

    var binding: ActivityTasksInformationBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksInformationBinding.inflate(layoutInflater, null, false)
        val view = binding?.root
        setContentView(view)

        if (intent.extras != null) {
            val loc = intent?.extras?.getString("location").toString()
            binding?.taskLocation?.text = loc
        }
    }
}