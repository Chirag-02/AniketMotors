package com.emploc.aniketmotor.login

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AniketMotorsLoginModule {
    @Binds
    @IntoMap
    @ViewModelKey(AniketMotorsLoginViewModel::class)
    abstract fun aniketMotorsLoginModule(viewModel: AniketMotorsLoginViewModel): ViewModel
}