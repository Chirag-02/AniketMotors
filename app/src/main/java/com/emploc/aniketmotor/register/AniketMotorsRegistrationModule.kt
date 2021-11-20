package com.emploc.aniketmotor.register

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AniketMotorsRegistrationModule {
    @Binds
    @IntoMap
    @ViewModelKey(AniketMotorsRegistrationViewModel::class)
    abstract fun aniketMotorsRegistrationModule(viewModel: AniketMotorsRegistrationViewModel): ViewModel
}