package com.example.chattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        var btnSignUp= findViewById<Button>(R.id.btnSignUp)
        var btnLogin= findViewById<Button>(R.id.btnLogin)
        var edtEmail = findViewById<EditText>(R.id.edtEmail)
        var edtPassword = findViewById<EditText>(R.id.edtPassword)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        mAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            login(email,password)
        }
    }

    private fun login(email: String, password: String) {
        //logic for logging user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, đưa về home
                    val intent =Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login,"User does not exist",Toast.LENGTH_SHORT).show()
                }
            }

    }
}