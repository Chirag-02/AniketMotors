package com.emploc.di


import com.emploc.aniketmotor.AniketMotorsHomeActivity
import com.emploc.aniketmotor.AniketMotorsHomeModule
import com.emploc.aniketmotor.login.AniketMotorsLoginActivity
import com.emploc.aniketmotor.login.AniketMotorsLoginModule
import com.emploc.aniketmotor.register.AniketMotorsRegistrationActivity
import com.emploc.aniketmotor.register.AniketMotorsRegistrationModule
import com.emploc.di.scoped.ActivityScoped
import com.emploc.screens.splash.SplashActivity
import com.emploc.screens.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun splashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [AniketMotorsHomeModule::class])
    abstract fun aniketMotorsHome(): AniketMotorsHomeActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [AniketMotorsLoginModule::class])
    abstract fun aniketMotorsLogin(): AniketMotorsLoginActivity


    @ActivityScoped
    @ContributesAndroidInjector(modules = [AniketMotorsRegistrationModule::class])
    abstract fun aniketMotorsRegistration(): AniketMotorsRegistrationActivity




}