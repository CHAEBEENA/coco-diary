package com.chaebeen.coco.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

open class ApplicationObserver(val context: Context) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {
        Log.d("coco-dev","APPLICATION ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        Log.d("coco-dev","APPLICATION ON_RESUME")
        //Toast.makeText(context, "resume", Toast.LENGTH_SHORT).show()
        context.startActivity(Intent(context, AppLockActivity::class.java))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        Log.d("coco-dev","APPLICATION ON_STOP")
    }


}

