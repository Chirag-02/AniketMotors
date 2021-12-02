package com.aniketassociates.aniketmotor.register

import androidx.lifecycle.ViewModel
import com.aniketassociates.di.ViewModelKey
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