package com.emploc.di

import com.emploc.EmplocApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<EmplocApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: EmplocApplication): Builder
        fun build(): AppComponent
    }
    override fun inject(application: EmplocApplication)

}