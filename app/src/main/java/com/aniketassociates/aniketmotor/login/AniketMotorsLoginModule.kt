package com.aniketassociates.aniketmotor.login

import androidx.lifecycle.ViewModel
import com.aniketassociates.di.ViewModelKey
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