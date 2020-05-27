package com.chaebeen.coco.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.chaebeen.coco.R
import com.chaebeen.coco.databinding.FragmentMovieBinding
import com.chaebeen.coco.ui.main.OnBackPressedListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment(), OnBackPressedListener {

    private val viewModel: MovieViewModel by viewModel()

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            Log.d("chae",it.toString())
        })

        binding.movieAddBtn.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.movie_fragment_container,
                PostMovieFragment()
            ).commit()
        }

    }

    override fun onBackPressed(): Boolean {
        childFragmentManager.fragments.forEach {
            return if((it as? OnBackPressedListener)?.onBackPressed() == true) true
            else {
                childFragmentManager.beginTransaction().remove(it).commit()
                true
            }
        }
        return false
    }

}
