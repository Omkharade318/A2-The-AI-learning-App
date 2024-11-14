package com.example.a2lytics

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2lytics.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Log_In : AppCompatActivity() {

    private val binding: ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }

    private lateinit var email : String
    private lateinit var password : String

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

            val loginButton = binding.LoginBtn
            val signUpButton = binding.SignUPBtn


            loginButton.setOnClickListener {
                // getting text from editText
                email = binding.emailText.text.toString().trim()
                password = binding.passwordText.text.toString().trim()

                if(email.isBlank() || password.isBlank()){
                    Toast.makeText(this, "Please fill all Details", Toast.LENGTH_SHORT).show()
                }else{
                    logIntoAccount(email, password)
                }
            }


        signUpButton.setOnClickListener {
                val intent = Intent(this@Log_In, Sign_Up::class.java)
                startActivity(intent)
        }
    }
    private fun logIntoAccount(email : String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()
                updateUI(user)
            }
            else{
                Toast.makeText(
                    this,
                    "Login Failed : ${task.exception}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(
            Intent(this, MainActivity::class.java)
        )
        finish()
    }
}