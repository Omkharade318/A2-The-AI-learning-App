package com.example.a2lytics.drawer

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.a2lytics.R
import com.example.a2lytics.data.User
import com.example.a2lytics.data.UserViewModel
import com.example.a2lytics.databinding.ActivityEditProfileBinding

class Edit_Profile : AppCompatActivity() {
    
    private val binding : ActivityEditProfileBinding by lazy {
        ActivityEditProfileBinding.inflate(layoutInflater)
    }

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.submitButton.setOnClickListener{
            insertDataToDataBase()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }


    }

    private fun insertDataToDataBase() {
        val name = binding.userName.text.toString()
        val mobileNo = binding.userMobileNo.text.toString()
        val email = binding.userEmail.text.toString()
        val address = binding.userAddress.text.toString()
        val schoolName = binding.schoolName.text.toString()
        val educationBoardName = binding.educationBoard.text.toString()
        val userClass = binding.userClass.text.toString()

        if(inputCheck(name, mobileNo, email, address, schoolName, educationBoardName, userClass)){
            val user = User(
                id = 0,
                userName = name,
                mobileNumber = mobileNo,
                email = email,
                address = address,
                schoolName = schoolName,
                educationBoard = educationBoardName,
                userClass = userClass
                )

            userViewModel.addUser(user)
            Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun  inputCheck(name: String, mobileNo: String, email: String, address: String, schoolName: String, educationBoardName: String, userClass: String): Boolean{
       return !(
               TextUtils.isEmpty(name) && TextUtils.isEmpty(mobileNo) &&
               TextUtils.isEmpty(email) && TextUtils.isEmpty(address) &&
               TextUtils.isEmpty(schoolName) && TextUtils.isEmpty(educationBoardName) &&
               TextUtils.isEmpty(userClass)
               )
    }
}