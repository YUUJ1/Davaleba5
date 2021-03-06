package com.example.a5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class ResetActivity : AppCompatActivity() {
    private lateinit var emailForRes : EditText
    private lateinit var resetButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
        emailForRes = findViewById(R.id.emailForRes)
        resetButton = findViewById(R.id.resetButton)

        resetButton.setOnClickListener {
            val emailCheck = emailForRes.text.toString()
            if (emailCheck.isEmpty()){
                Toast.makeText(this, "Please enter your e-mail and try again", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(emailCheck)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}