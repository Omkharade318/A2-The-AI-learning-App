package com.example.a2lytics

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2lytics.databinding.ActivitySignUpBinding

class Sign_Up : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val createAccountButton = binding.createAccountBtn
        val signInButton = binding.signINBtn

//         Temporary
        createAccountButton.setOnClickListener {

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@Sign_Up, MainActivity::class.java)
                startActivity(intent)
            }, 500)

        }

//         Temporary
        signInButton.setOnClickListener {

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@Sign_Up, Log_In::class.java)
                startActivity(intent)
            }, 500)

        }
    }
}


