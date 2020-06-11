package com.chaebeen.coco.ui.setting

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.chaebeen.coco.R
import com.chaebeen.coco.data.prefs.PreferenceManager
import kotlinx.android.synthetic.main.activity_app_lock.*

class ChangePasswordActivity: AppCompatActivity() {

    private val prefs = PreferenceManager(this)

    private var state = MutableLiveData<PasswordState>()

    private var newPassword : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_lock)

        state.postValue(PasswordState.CHANGE)

        if(prefs.isLock(this)){
            lock_text.text = "기존 비밀번호를 입력하세요"
            lock_confirm_btn.setOnClickListener {
                if(prefs.getPassword(this, "password") == lock_edit_text.text.toString()) {
                    changePassword()
                    lock_edit_text.text.clear()
                } else {
                    Toast.makeText(this, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
                    lock_edit_text.text.clear()
                }
            }
        } else {
            changePassword()
        }

    }

    private fun changePassword() {
        state.observe(this, Observer {
            when(it) {
                PasswordState.CHANGE -> {
                    lock_text.text = "새로운 비밀번호를 입력하세요"
                    lock_confirm_btn.setOnClickListener {
                        state.postValue(PasswordState.CONFIRM)
                        newPassword = lock_edit_text.text.toString()
                        lock_edit_text.text.clear()
                    }
                }
                PasswordState.CONFIRM -> {
                    lock_text.text = "다시 한번 입력해주세요"
                    lock_confirm_btn.setOnClickListener {
                        if(newPassword == lock_edit_text.text.toString()) {
                            prefs.setPassword(this, "password", lock_edit_text.text.toString())
                            finish()
                        } else {
                            Toast.makeText(this, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
                            lock_edit_text.text.clear()
                            state.postValue(PasswordState.CHANGE)
                        }
                    }
                }
            }
        })
    }

}

enum class PasswordState{
    CHANGE,
    CONFIRM,
    RETRY
}