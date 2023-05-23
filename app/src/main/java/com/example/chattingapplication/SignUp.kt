package com.example.chattingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        var btnSignUp= findViewById<Button>(R.id.btnSignUp)
        var edtEmail = findViewById<EditText>(R.id.edtEmail)
        var edtPassword = findViewById<EditText>(R.id.edtPassword)
        var edtName = findViewById<EditText>(R.id.edtName)
        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val name = edtName.text.toString()

            singUp(name,email, password)
        }
        mAuth=FirebaseAuth.getInstance()
    }

    private fun singUp(name:String,email: String, password: String) {
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, đưa về home
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent =Intent(this@SignUp,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp,"Some error occurred", Toast.LENGTH_SHORT).show()

                }
            }

    }

    private fun addUserToDatabase(name: String, email: String, uid: String?) {
        mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid!!).setValue(user(name,email,uid))

    }
}