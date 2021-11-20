package com.emploc.screens.loginmodule.signin

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignInModule{
    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun signinModule(viewModel: SignInViewModel) : ViewModel

//    @ContributesAndroidInjector
//    abstract fun contributeFragment():FirstFragment
}