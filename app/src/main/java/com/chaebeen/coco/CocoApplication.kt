package com.chaebeen.coco

import android.app.Application
import com.chaebeen.coco.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CocoApplication :Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CocoApplication)

            androidLogger()

            androidFileProperties()

            modules(appModule)
        }
    }
}
