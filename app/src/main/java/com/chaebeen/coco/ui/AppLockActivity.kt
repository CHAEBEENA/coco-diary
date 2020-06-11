package com.chaebeen.coco.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chaebeen.coco.R
import com.chaebeen.coco.data.prefs.PreferenceManager
import kotlinx.android.synthetic.main.activity_app_lock.*


class AppLockActivity:AppCompatActivity() {

    private val prefs = PreferenceManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_lock)

        lock_text.text = "비밀번호를 입력하세요"

        lock_confirm_btn.setOnClickListener {
            if(prefs.getPassword(this, "password") == lock_edit_text.text.toString()) {
                finish()
            }
           // prefs.setPassword(this, "password", lock_edit_text.text.toString())
        }

    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}