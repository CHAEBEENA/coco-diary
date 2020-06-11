package com.chaebeen.coco.ui.setting

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chaebeen.coco.R
import com.chaebeen.coco.data.prefs.PreferenceManager
import kotlinx.android.synthetic.main.activity_app_lock.*

class ChangePasswordActivity: AppCompatActivity(), View.OnClickListener {

    private val prefs = PreferenceManager(this)

    private lateinit var state : PasswordState

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_app_lock)

        state = PasswordState.CHANGE

        lock_edit_text.text

        if(prefs.isLock){
            lock_text.text = "기존 비밀번호를 입력하세요"
            lock_confirm_btn.setOnClickListener {
                if(prefs.getPassword(this, "password") == lock_edit_text.text.toString()) {
                    lock_text.text = "새로운 비밀번호를 입력하세요"
                    onClick(lock_confirm_btn)
                }
                prefs.setPassword(this, "password", lock_edit_text.text.toString())
            }
        }

        lock_confirm_btn.setOnClickListener {
            prefs.setPassword(this, "password", lock_edit_text.text.toString())
        }

    }

    override fun onClick(v: View?) {
        if(state == PasswordState.CHANGE) {
            lock_text.text = "새로운 비밀번호를 입력하세요"
            state = PasswordState.CONFIRM
        }
        if(state == PasswordState.CONFIRM) {
            lock_text.text = ""
        }
    }
}

enum class PasswordState{
    CHANGE,
    CONFIRM
}