package com.example.a5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var logInButton : Button
    private lateinit var rregistration : Button
    private lateinit var resPassword : Button
    private lateinit var email : EditText
    private lateinit var password : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        logInButton = findViewById(R.id.logInButton)
        rregistration = findViewById(R.id.rregistration)
        resPassword = findViewById(R.id.resPassword)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        logInButton.setOnClickListener {
            val email : String = email.text.toString()
            val password : String = password.text.toString()

            if (email.isEmpty() && password.isEmpty()){

                Toast.makeText(this, "Please enter your info", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        homePage()
                    }
                    else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        Log.d("login_tag", task.exception.toString())
                    }
                }



        }
        rregistration.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        }
        resPassword.setOnClickListener {
            startActivity(Intent(this, ResetActivity::class.java))

        }
    }
    private fun homePage(){
        startActivity(Intent(this, LoggedinActivity::class.java))
        finish()
    }


}