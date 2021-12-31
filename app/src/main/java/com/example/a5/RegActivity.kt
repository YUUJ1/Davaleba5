package com.example.a5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class RegActivity : AppCompatActivity() {
    private lateinit var emailReg : EditText
    private lateinit var newPassword : EditText
    private lateinit var repeatPassword : EditText
    private lateinit var regButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        init()
    }
    private fun init(){
        emailReg = findViewById(R.id.emailReg)
        newPassword = findViewById(R.id.newPassword)
        repeatPassword = findViewById(R.id.repeatPassword)
        regButton = findViewById(R.id.regButton)

        regButton.setOnClickListener {
            val eCheck : String = emailReg.text.toString()
            val pCheck : String = newPassword.text.toString()
            val rpCheck : String = repeatPassword.text.toString()
            if (eCheck.isEmpty() && pCheck.isEmpty() && rpCheck.isEmpty()){

                Toast.makeText(this, "Please fill all bars and try again", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pCheck != rpCheck){
                Toast.makeText(this, "Passwords are not the same please try again", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pCheck.length < 6){
                Toast.makeText(this, "Password has to be at least with 6 symbols", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(eCheck, pCheck)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, LoggedinActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}