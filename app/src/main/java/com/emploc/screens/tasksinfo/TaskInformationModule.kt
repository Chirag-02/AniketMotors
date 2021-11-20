package com.emploc.screens.tasksinfo

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
abstract class TaskInformationModule {
    @Binds
    @IntoMap
    @ViewModelKey(TaskInfromationViewModel::class)
    abstract fun taskInfoModule(viewModel: TaskInfromationViewModel): ViewModel


}