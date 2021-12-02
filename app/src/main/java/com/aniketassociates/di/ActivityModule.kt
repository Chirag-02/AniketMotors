package com.aniketassociates.di


import com.aniketassociates.aniketmotor.AniketMotorsHomeActivity
import com.aniketassociates.aniketmotor.AniketMotorsHomeModule
import com.aniketassociates.aniketmotor.login.AniketMotorsLoginActivity
import com.aniketassociates.aniketmotor.login.AniketMotorsLoginModule
import com.aniketassociates.aniketmotor.register.AniketMotorsRegistrationActivity
import com.aniketassociates.aniketmotor.register.AniketMotorsRegistrationModule
import com.aniketassociates.di.scoped.ActivityScoped
import com.aniketassociates.screens.splash.SplashActivity
import com.aniketassociates.screens.splash.SplashModule
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