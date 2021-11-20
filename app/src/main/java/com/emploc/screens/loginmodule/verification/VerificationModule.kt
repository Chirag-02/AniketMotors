package com.emploc.screens.loginmodule.verification

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VerificationModule{
    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel::class)
    abstract fun signinModule(viewModel: VerificationViewModel) : ViewModel

//    @ContributesAndroidInjector
//    abstract fun contributeFragment():FirstFragment
}