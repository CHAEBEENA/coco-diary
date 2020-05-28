package com.chaebeen.coco.ui.movie

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.chaebeen.coco.data.database.model.MovieEntity
import com.chaebeen.coco.databinding.FragmentPostMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class PostMovieFragment : Fragment() {

    companion object {
        fun newInstance() = PostMovieFragment()
    }

    var PICK_IMAGE_FROM_ALBUM = 0

    var photoUri : Uri? = null

    private lateinit var binding: FragmentPostMovieBinding

    private val viewModel: PostMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostMovieBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.postmoviePosterImg.setOnClickListener {
            //Open the album
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)
        }

        binding.postmovieCompleteBtn.setOnClickListener {
            contentUpload()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_FROM_ALBUM) {
            if(resultCode == Activity.RESULT_OK) {
                //선택한 이미지 경로
                photoUri = data?.data
                Glide.with(binding.postmoviePosterImg).load(photoUri).centerCrop().into(binding.postmoviePosterImg)
                //binding.postmoviePosterImg.setImageURI(photoUri)
            } else {
                //취소버튼 눌렀을때

            }
        }
    }

    fun contentUpload() {
        Toast.makeText(requireContext(), "upload success", Toast.LENGTH_LONG).show()
        var movieItem = MovieEntity(binding.postmovieTitle.text.toString(), photoUri.toString())
        viewModel.insertMovie(movieItem)
/*
        childFragmentManager.apply {
            beginTransaction().remove(this@PostMovieFragment).commit()
            popBackStack()
        }
        */
    }

}
