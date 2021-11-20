package com.emploc.screens.editprofile

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.donthaveaccount.DontHaveAccountViewModel
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EditProfileModule{
    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun editProfileModule(viewModel: EditProfileViewModel) : ViewModel

//    @ContributesAndroidInjector
//    abstract fun contributeFragment():FirstFragment
}