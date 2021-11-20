package com.emploc.screens.donthaveaccount

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.donthaveaccount.DontHaveAccountViewModel
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DontHaveAccountModule{
    @Binds
    @IntoMap
    @ViewModelKey(DontHaveAccountViewModel::class)
    abstract fun dontHaveModule(viewModel: DontHaveAccountViewModel) : ViewModel

//    @ContributesAndroidInjector
//    abstract fun contributeFragment():FirstFragment
}