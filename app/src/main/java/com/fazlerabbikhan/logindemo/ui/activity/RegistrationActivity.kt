package com.fazlerabbikhan.logindemo.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fazlerabbikhan.logindemo.R
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.fazlerabbikhan.logindemo.ui.MyApplication
import com.fazlerabbikhan.logindemo.viewmodel.RegistrationViewModel
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {
    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var mobileEditText: EditText
    private lateinit var passwordEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        (applicationContext as MyApplication).appComponent.inject(this)

        usernameEditText = findViewById(R.id.regUsername)
        emailEditText = findViewById(R.id.regEmail)
        mobileEditText = findViewById(R.id.regMobile)
        passwordEditText = findViewById(R.id.regPassword)

        val signUpButton: Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            // Handle sign up button click
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val mobile = mobileEditText.text.toString()
            val password = passwordEditText.text.toString()

            registrationViewModel.register(username, email, mobile, password)

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val loginLink: TextView = findViewById(R.id.regToLogin)
        loginLink.setOnClickListener {
            // Handle login link click
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}