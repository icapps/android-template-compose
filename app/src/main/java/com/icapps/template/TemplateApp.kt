package com.icapps.template

import android.app.Application
import com.chimerapps.niddler.core.AndroidNiddler
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class TemplateApp : Application() {

    @Inject
    lateinit var niddler: AndroidNiddler

    override fun onCreate() {
        super.onCreate()
        // Attach Niddler
        niddler.attachToApplication(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
