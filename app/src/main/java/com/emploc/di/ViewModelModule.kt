package com.emploc.di

import androidx.lifecycle.ViewModelProvider
import com.emploc.di.OurViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: OurViewModelFactory): ViewModelProvider.Factory
}