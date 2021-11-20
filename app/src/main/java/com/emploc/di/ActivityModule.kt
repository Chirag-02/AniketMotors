package com.emploc.di


import com.emploc.aniketmotor.AniketMotorsHomeActivity
import com.emploc.aniketmotor.AniketMotorsHomeModule
import com.emploc.aniketmotor.login.AniketMotorsLoginActivity
import com.emploc.aniketmotor.login.AniketMotorsLoginModule
import com.emploc.aniketmotor.register.AniketMotorsRegistrationActivity
import com.emploc.aniketmotor.register.AniketMotorsRegistrationModule
import com.emploc.di.scoped.ActivityScoped
import com.emploc.screens.choosesignupmode.ChooseSignUpActivityModule
import com.emploc.screens.choosesignupmode.ChooseSignupModeActivity
import com.emploc.screens.donthaveaccount.DontHaveAccountActivity
import com.emploc.screens.donthaveaccount.DontHaveAccountModule
import com.emploc.screens.editprofile.EditProfileActivity
import com.emploc.screens.editprofile.EditProfileModule
import com.emploc.screens.home.HomeActivity
import com.emploc.screens.home.HomeActivityModule
import com.emploc.screens.loginmodule.devicepin.DevicePinModule
import com.emploc.screens.loginmodule.devicepin.DevicePinActivity
import com.emploc.screens.loginmodule.signin.SignInActivity
import com.emploc.screens.loginmodule.signin.SignInModule
import com.emploc.screens.loginmodule.verification.VerificationActivity
import com.emploc.screens.loginmodule.verification.VerificationModule
import com.emploc.screens.signupform.SignUpActivity
import com.emploc.screens.signupform.SignUpActivityModule
import com.emploc.screens.slider.SliderActivity
import com.emploc.screens.slider.SliderScreenModule
import com.emploc.screens.splash.SplashActivity
import com.emploc.screens.splash.SplashModule
import com.emploc.screens.tasksinfo.TaskInformationModule
import com.emploc.screens.tasksinfo.TasksInformationActivity
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

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SliderScreenModule::class])
    abstract fun sliderActivity(): SliderActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [DontHaveAccountModule::class])
    abstract fun dontHaveACtivity(): DontHaveAccountActivity

//    @ContributesAndroidInjector
//    abstract fun contributeMyFirebaseMessagingService(): MyFirebaseMessagingService

    @ActivityScoped
    @ContributesAndroidInjector(modules = [ChooseSignUpActivityModule::class])
    abstract fun chooseModeActivity(): ChooseSignupModeActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SignUpActivityModule::class])
    abstract fun signUpActivity(): SignUpActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivity(): HomeActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TaskInformationModule::class])
    abstract fun taskInfoActivity(): TasksInformationActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SignInModule::class])
    abstract fun signInActivity(): SignInActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [VerificationModule::class])
    abstract fun verificationActivity(): VerificationActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [DevicePinModule::class])
    abstract fun deviceModuleActivity(): DevicePinActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [EditProfileModule::class])
    abstract fun editProfileActivity(): EditProfileActivity


}