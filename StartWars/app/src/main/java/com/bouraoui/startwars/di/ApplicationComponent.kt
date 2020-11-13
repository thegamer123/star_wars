package com.bouraoui.startwars.di

import android.app.Activity
import android.content.Context
import com.bouraoui.startwars.MainActivity
import com.bouraoui.startwars.data.remote.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubComponents::class,
    NetworkModule::class,
    AndroidSupportInjectionModule::class])
interface ApplicationComponent {


    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context, @BindsInstance currentActivity: Activity): ApplicationComponent
    }

    fun movieComponent(): MovieComponent.Factory
}