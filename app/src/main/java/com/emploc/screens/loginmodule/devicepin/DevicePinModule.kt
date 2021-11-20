package com.emploc.screens.loginmodule.devicepin

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DevicePinModule{
    @Binds
    @IntoMap
    @ViewModelKey(DeviceViewModel::class)
    abstract fun signinModule(viewModel: DeviceViewModel) : ViewModel

//    @ContributesAndroidInjector
//    abstract fun contributeFragment():FirstFragment
}