package com.chaebeen.coco.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chaebeen.coco.R
import com.chaebeen.coco.databinding.ActivityMainBinding
import com.chaebeen.coco.ui.add.AddFragment
import com.chaebeen.coco.ui.book.BookFragment
import com.chaebeen.coco.ui.calendar.CalendarFragment
import com.chaebeen.coco.ui.calendar.scrollcalendar.ScrollCalendarFragment
import com.chaebeen.coco.ui.movie.MovieFragment
import com.chaebeen.coco.ui.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("coco-dev","OnCreate")

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this

        binding.mainCalendarNavBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                CalendarFragment()
            ).commit()
        }
        binding.mainBookNavBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                BookFragment()
            ).commit()
        }
        binding.mainMovieNavBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                MovieFragment()
            ).commit()
        }
        binding.mainScrollCalendarNavBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                ScrollCalendarFragment()
            ).commit()
        }
        binding.mainAddBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                AddFragment()
            ).commit()
        }
        binding.mainSettingNavBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                SettingFragment()
            ).commit()
        }

    }


    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            val consumed = (it as? OnBackPressedListener)?.onBackPressed() == true

            if(!consumed) {
              //  Log.d("coco-dev",it.tag)
                supportFragmentManager.beginTransaction().remove(it).commit()
                return
            }
        }

        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        Log.d("coco-dev","OnResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("coco-dev","OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("coco-dev","OnStop")

    }

}


