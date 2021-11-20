package com.emploc.di

import android.content.Context
import com.emploc.EmplocApplication
import com.emploc.networking.EmplocDataServices
import com.emploc.util.Constant
import com.emploc.util.EmplocPreferences

import dagger.Module
import dagger.Provides

@Module
class AppModule{
    @Provides
    fun provideApi(kabadiplusPreferences: EmplocPreferences): EmplocDataServices {
        return  EmplocDataServices.invoke(kabadiplusPreferences.getString(Constant.TOKEN))
    }

    @Provides
    fun contextApp(application: EmplocApplication): Context {
        return application
    }

}