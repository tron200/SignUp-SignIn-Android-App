package com.example.signup_signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val usernameView = findViewById(R.id.username_text_view) as TextView
        val emailView = findViewById(R.id.email_text_view) as TextView
        val gendreView = findViewById(R.id.gendre_text_view) as TextView
        val passwordView = findViewById(R.id.password_text_view) as TextView
        val backButton = findViewById(R.id.back_button) as Button
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val gendre = intent.getStringExtra("gendre")
        val password = intent.getStringExtra("password")
        usernameView.setText("Username: $username")
        emailView.setText("Email: $email")
        gendreView.setText(when(gendre) {
            "true" -> "Gender: Male"
            else -> "Gender: Female"
        })
        passwordView.setText("Password: $password")
        backButton.setOnClickListener{
            finish()
        }
    }
}