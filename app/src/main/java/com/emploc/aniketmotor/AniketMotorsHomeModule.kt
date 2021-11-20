package com.emploc.aniketmotor

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AniketMotorsHomeModule {
    @Binds
    @IntoMap
    @ViewModelKey(AniketMotorsHomeViewModel::class)
    abstract fun splashModule(viewModel: AniketMotorsHomeViewModel): ViewModel
}