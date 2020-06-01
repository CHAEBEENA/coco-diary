package com.chaebeen.coco.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.chaebeen.coco.databinding.FragmentDetailMovieBinding
import com.chaebeen.coco.ui.main.OnBackPressedListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    companion object {
        fun newInstance() = DetailMovieFragment()
    }

    private val viewModel: DetailMovieViewModel by viewModel()

    private val movieViewModel: MovieViewModel by inject()

    private lateinit var binding: FragmentDetailMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieViewModel.movieItem.observe(viewLifecycleOwner, Observer {
            binding.detailmovieTitle.text = it.title
            Glide.with(requireContext()).load(it.posterUrl).centerCrop().into(binding.detailmoviePoster)
            viewModel.getItem(it)
        })

        binding.detailmovieDelete.setOnClickListener {
            viewModel.currentItem.value?.let {
                Toast.makeText(requireContext(), "Delete Movie",Toast.LENGTH_SHORT).show()
                movieViewModel.delete(it)
                (requireParentFragment() as? OnBackPressedListener)?.onBackPressed()
            }
        }
    }

}
