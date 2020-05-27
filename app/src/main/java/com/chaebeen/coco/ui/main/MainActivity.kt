package com.chaebeen.coco.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.chaebeen.coco.R
import com.chaebeen.coco.databinding.ActivityMainBinding
import com.chaebeen.coco.ui.all.AllFragment
import com.chaebeen.coco.ui.book.BookFragment
import com.chaebeen.coco.ui.movie.MovieFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this

        binding.mainAllNavBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                AllFragment()
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

    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            val consumed = (it as? OnBackPressedListener)?.onBackPressed() == true

            if(!consumed) {
                supportFragmentManager.beginTransaction().remove(it).commit()
                return
            }
        }

        super.onBackPressed()
    }

}
