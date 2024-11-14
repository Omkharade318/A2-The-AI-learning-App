package com.example.a2lytics

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2lytics.databinding.ActivitySignUpBinding
import com.example.a2lytics.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class Sign_Up : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    private lateinit var email : String
    private lateinit var password : String
    private lateinit var userName : String
    private lateinit var phoneNo : String

    private lateinit var auth : FirebaseAuth
    private lateinit var dataBase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // initializing Firebase Auth
        auth = Firebase.auth

        // initializing Firebase Database
        dataBase = Firebase.database.reference

        val createAccountButton = binding.createAccountBtn
        val signInButton = binding.signINBtn

        createAccountButton.setOnClickListener {
            // getting text from editText
            email = binding.emailText.text.toString().trim()
            password = binding.passwordText.text.toString().trim()
            userName = binding.nameText.text.toString().trim()
            phoneNo = binding.phoneText.text.toString().trim()

            // checking if email, password, userName and phoneNo are empty
            if (email.isBlank() || userName.isBlank() || password.isBlank() || phoneNo.isBlank()) {
                Toast.makeText(
                    this,
                    "Please fill all details",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                createAccount(email, password)
            }
        }

        signInButton.setOnClickListener {
                val intent = Intent(this@Sign_Up, Log_In::class.java)
                startActivity(intent)
        }
    }

    private fun createAccount(email : String, password : String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Account created successfully",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@Sign_Up, Log_In::class.java)
                saveUserData()
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(
                    this,
                    "Account Creation Failed : ${task.exception}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("Account", "createAccount: Failure - ${task.exception}")
            }
        }
    }

    //saving user data to database
    private fun saveUserData() {
        // getting text from editText
        email = binding.emailText.text.toString().trim()
        password = binding.passwordText.text.toString().trim()
        userName = binding.nameText.text.toString().trim()
        phoneNo = binding.phoneText.text.toString().trim()

        val user = UserModel(
            email = email,
            password = password,
            userName = userName,
            phoneNo = phoneNo
        )
        // saving user data to Firebase database
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        dataBase.child("user").child(userId).setValue(user)

    }
}


