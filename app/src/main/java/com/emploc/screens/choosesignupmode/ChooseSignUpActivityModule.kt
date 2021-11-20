package com.emploc.screens.choosesignupmode

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.donthaveaccount.DontHaveAccountViewModel
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChooseSignUpActivityModule{
    @Binds
    @IntoMap
    @ViewModelKey(ChooseSignupModeViewModel::class)
    abstract fun chooseSignupModeModule(viewModel: ChooseSignupModeViewModel) : ViewModel

//    @ContributesAndroidInjector
//    abstract fun contributeFragment():FirstFragment
}