package com.example.a2lytics.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.a2lytics.R
import com.example.a2lytics.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

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

        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Get Current User ID
        val currentUserId = auth.currentUser?.uid

        if (currentUserId != null) {
            // Fetch user name from Firebase
            database.child("user").child(currentUserId).child("userName")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userName = snapshot.value?.toString()
                        if (userName != null) {
                            // Update TextView with the user's name
                            binding.userName.text = "Hi, $userName"
                        } else {
                            binding.userName.text = "Hi, User"
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, "Failed to fetch user data: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            binding.userName.text = "Hi"
        }

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
}
