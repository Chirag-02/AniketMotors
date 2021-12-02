package com.aniketassociates.di

import android.content.Context
import com.aniketassociates.EmplocApplication
import com.aniketassociates.networking.EmplocDataServices
import com.aniketassociates.util.Constant
import com.aniketassociates.util.EmplocPreferences

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