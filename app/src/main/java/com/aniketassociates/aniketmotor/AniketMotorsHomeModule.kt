package com.aniketassociates.aniketmotor

import androidx.lifecycle.ViewModel
import com.aniketassociates.di.ViewModelKey
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