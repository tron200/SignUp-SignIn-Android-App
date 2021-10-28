package com.example.signup_signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var signIn = false
        val hyper: TextView = findViewById(R.id.hyper_link)
        val header: TextView = findViewById(R.id.head)
        val username: EditText = findViewById(R.id.username_edit_text)
        val email: EditText = findViewById(R.id.email_edit_text)
        val password: EditText = findViewById(R.id.password_edit_text)
        val gender:RadioGroup = findViewById(R.id.radioGroup)
        val signButton : Button = findViewById(R.id.sign_button)
        hyper.setOnClickListener {
            if (signIn){
                signIn = false
                hyper.setText(R.string.sign_up_insted)
                header.setText(R.string.sign_in)
                signButton.setText(R.string.sign_in)
                email.visibility = View.GONE
                gender.visibility = View.GONE
            }else{
                signIn = true
                header.setText(R.string.sign_up)
                hyper.setText(R.string.sign_in_insted)
                signButton.setText(R.string.sign_up)
                email.visibility = View.VISIBLE
                gender.visibility = View.VISIBLE
            }
        }
    }
}