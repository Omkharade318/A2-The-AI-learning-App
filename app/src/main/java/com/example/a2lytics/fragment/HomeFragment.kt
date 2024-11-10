package com.example.a2lytics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.a2lytics.R
import com.example.a2lytics.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up startLearningBtn click listener
        binding.startLearningBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_basicFragment)
        }

        // Set up ImageSlider
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.a2_pic1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.a2_pic2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.a2_pic3, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.a2_pic4, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

    }


    companion object {

    }

}