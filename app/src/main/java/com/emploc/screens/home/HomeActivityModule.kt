package com.emploc.screens.home

import androidx.lifecycle.ViewModel
import com.emploc.di.ViewModelKey
import com.emploc.screens.bottomtab.account.MyAccountFragment
import com.emploc.screens.bottomtab.account.MyAccountViewModel
import com.emploc.screens.bottomtab.attendance.AttendanceFragment
import com.emploc.screens.bottomtab.attendance.AttendanceViewModel
import com.emploc.screens.bottomtab.tasks.TasksFragment
import com.emploc.screens.bottomtab.tasks.TasksViewModel
import com.emploc.screens.bottomtab.track.TrackFragment
import com.emploc.screens.bottomtab.track.TrackViewModel
import com.emploc.screens.donthaveaccount.DontHaveAccountViewModel
import com.emploc.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class HomeActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    abstract fun signupModeModule(viewModel: HomeActivityViewModel): ViewModel


    @ContributesAndroidInjector
    abstract fun attendanceTabFrag(): AttendanceFragment

    @Binds
    @IntoMap
    @ViewModelKey(AttendanceViewModel::class)
    abstract fun attendanceFragModule(viewModel: AttendanceViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun trackTabFrag(): TrackFragment

    @Binds
    @IntoMap
    @ViewModelKey(TrackViewModel::class)
    abstract fun trackFragModule(viewModel: TrackViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun myAccountTabFrag(): MyAccountFragment

    @Binds
    @IntoMap
    @ViewModelKey(MyAccountViewModel::class)
    abstract fun myAccountFragModule(viewModel: MyAccountViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun tasksTabFrag(): TasksFragment

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun tasksFragModule(viewModel: TasksViewModel): ViewModel
}