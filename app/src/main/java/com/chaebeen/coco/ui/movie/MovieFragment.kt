package com.chaebeen.coco.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.chaebeen.coco.R
import com.chaebeen.coco.databinding.FragmentMovieBinding
import com.chaebeen.coco.ui.main.OnBackPressedListener
import com.chaebeen.coco.util.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), OnBackPressedListener {

    private val viewModel: MovieViewModel by viewModel()

    private lateinit var binding: FragmentMovieBinding

    private lateinit var mAdapter: MovieRecyclerViewAdapter

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

        mAdapter = MovieRecyclerViewAdapter(viewModel, Glide.with(requireActivity().baseContext))

        binding.movieRecyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 3)
        }

      //  viewModel.deleteAll()

        viewModel.getAll().observe(viewLifecycleOwner, Observer {
            Log.d("coco-dev",it.toString())
            mAdapter.items = it
        })

        binding.movieAddBtn.setOnClickListener {

           /* if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(requireContext(), PostMovieViewModel::class.java))
            }*/
            startActivity(Intent(requireContext(), PostMovieActivity::class.java))
        }

        viewModel.navigateToDetailMovieFragment.observe(viewLifecycleOwner, EventObserver{
            childFragmentManager.beginTransaction().replace(R.id.movie_fragment_container,
                DetailMovieFragment()
            ).commit()
        })
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
