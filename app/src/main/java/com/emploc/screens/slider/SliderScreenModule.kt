package com.emploc.screens.slider

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SliderScreenModule {


    @Binds
    @IntoMap
    @ViewModelKey(SliderViewModel::class)
    abstract fun sliderModule(viewModel: SliderViewModel) : ViewModel

}