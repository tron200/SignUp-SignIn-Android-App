package com.example.signup_signin

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
        val male: RadioButton = findViewById(R.id.radio_male)
        val db = Firebase.firestore

        hyper.setOnClickListener {
            if (signIn){
                hyper.setText(R.string.sign_in_insted)
                header.setText(R.string.sign_up)
                signButton.setText(R.string.sign_up)
                username.visibility = View.VISIBLE
                gender.visibility = View.VISIBLE
                username.setText("")
                email.setText("")
                password.setText("")
                male.isChecked = true
                signIn = false
            }else{
                header.setText(R.string.sign_in)
                hyper.setText(R.string.sign_up_insted)
                signButton.setText(R.string.sign_in)
                username.visibility = View.GONE
                gender.visibility = View.GONE
                username.setText("")
                email.setText("")
                password.setText("")
                signIn = true
            }
        }
            signButton.setOnClickListener {
                if(signIn){
                    val emailS : String = email.text.toString()
                    val passwordS : String = password.text.toString()
                    var found = false
                    db.collection("Users")
                        .whereEqualTo("email", emailS) // <-- This line
                        .get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                for (document in task.result!!) {
                                    if (document.getData().getValue("email").equals(emailS)){
                                        if(document.getData().getValue("password").equals(passwordS)) {
                                            found = true
                                            Toast.makeText(this, "Enter", Toast.LENGTH_SHORT)
                                                .show()
                                            val intent = Intent(this , profile::class.java).apply {
                                                putExtra("username", document.getData().get("userName").toString())
                                                putExtra("password", document.getData().get("password").toString())
                                                putExtra("gendre", document.getData().get("male").toString())
                                                putExtra("email", document.getData().get("email").toString())
                                            }
                                            username.setText("")
                                            email.setText("")
                                            password.setText("")
                                            startActivity(intent)
                                        }
                                }
                                if(!found){
                                    Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                }


                }else{
                    if(email.text.toString().isBlank() || password.text.toString().isBlank()
                        || username.text.toString().isBlank()){
                        Toast.makeText(this, "Please Fill Empty Fields ", Toast.LENGTH_SHORT).show()
                    }else{
                        val emailS : String = email.text.toString()
                        val maleB : Boolean = male.isChecked
                        val passwordS : String = password.text.toString()
                        val userNameS : String = username.text.toString()
                        var found = false

                        db.collection("Users")
                            .whereEqualTo("email", emailS) // <-- This line
                            .get()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    for (document in task.result!!) {
                                        if (document.getData().getValue("email").equals(emailS)){
                                            found = true
                                            Toast.makeText(this, "Sorry, this email already exist!!", Toast.LENGTH_SHORT).show()
                                        }else{
                                            found = false
                                        }
                                    }
                                    if(!found){
                                        val data = hashMapOf(
                                            "email" to emailS,
                                            "male" to maleB,
                                            "password" to passwordS,
                                            "userName" to userNameS
                                        )

                                        db.collection("Users")
                                            .add(data)
                                            .addOnSuccessListener {
                                                Toast.makeText(this, "Done, you are added!", Toast.LENGTH_SHORT).show()
                                                signIn = false
                                                hyper.callOnClick()
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(this, "sorry there is a problem, Check internet then try again !", Toast.LENGTH_SHORT).show()
                                            }
                                    }
                                } else {
                                    Toast.makeText(this, "Sorry there is a problem! try again later", Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                }
            }
    }

}


