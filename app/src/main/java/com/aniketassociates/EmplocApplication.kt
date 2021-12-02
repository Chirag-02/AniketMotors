package com.aniketassociates

import com.aniketassociates.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class EmplocApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out EmplocApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}

