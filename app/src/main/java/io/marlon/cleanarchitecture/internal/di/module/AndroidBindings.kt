package io.marlon.cleanarchitecture.internal.di.module

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.preference.PreferenceManager
import android.telephony.TelephonyManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidBindings {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideTelephonyManager(context: Context): TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    @Provides
    @Singleton
    fun provideLocationManager(context: Context): LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

}