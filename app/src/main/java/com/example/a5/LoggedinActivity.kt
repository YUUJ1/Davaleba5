package com.example.a5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class LoggedinActivity : AppCompatActivity() {

    private  lateinit var LogoutButton : Button
    private lateinit var  ResetButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggedin)

        LogoutButton = findViewById(R.id.logout)
        ResetButton = findViewById(R.id.resPassword)

        LogoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        ResetButton.setOnClickListener{
            startActivity(Intent(this, ResetActivity::class.java))
        }
    }
}