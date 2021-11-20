package com.emploc

import com.emploc.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class EmplocApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out EmplocApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}

