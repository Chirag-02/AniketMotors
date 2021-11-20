package com.emploc.screens.splash

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule{
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashModule(viewModel: SplashViewModel) : ViewModel

//    @ContributesAndroidInjector
//    abstract fun contributeFragment():FirstFragment
}