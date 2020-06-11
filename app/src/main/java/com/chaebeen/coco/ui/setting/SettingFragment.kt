package com.chaebeen.coco.ui.setting

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chaebeen.coco.R
import com.chaebeen.coco.data.prefs.PreferenceManager
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)
        // TODO: Use the ViewModel

        val prefs = PreferenceManager(requireContext())

        settings_password_switch.isChecked = prefs.isLock(requireContext())

        settings_password_text.setOnClickListener {
            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
        }

        settings_password_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
            }else{
                prefs.removeKey(requireContext())
            }
        }

    }

}
