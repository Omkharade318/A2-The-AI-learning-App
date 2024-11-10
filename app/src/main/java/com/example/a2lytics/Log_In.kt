package com.example.a2lytics

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2lytics.databinding.ActivityLogInBinding

class Log_In : AppCompatActivity() {

    private val binding: ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
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


            val loginButton = binding.LoginBtn
            val signUpButton = binding.SignUPBtn


//         Temporary
            loginButton.setOnClickListener {

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this@Log_In, MainActivity::class.java)
                    startActivity(intent)
                }, 500)

            }


//         Temporary
        signUpButton.setOnClickListener {

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@Log_In, Sign_Up::class.java)
                startActivity(intent)
            }, 500)

        }
    }
}