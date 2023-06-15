package com.fazlerabbikhan.logindemo.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.fazlerabbikhan.logindemo.R
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fazlerabbikhan.logindemo.ui.MyApplication
import com.fazlerabbikhan.logindemo.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var loginViewModel: LoginViewModel

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (applicationContext as MyApplication).appComponent.inject(this)

        usernameEditText = findViewById(R.id.loginUsername)
        passwordEditText = findViewById(R.id.loginPassword)

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            loginViewModel.loginUser(username, password)
        }

        val signUpLink: TextView = findViewById(R.id.loginToSignUp)
        signUpLink.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.loginResult.observe(this, Observer { loginResult ->
            if (loginResult) {
                // Login successful
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Login failed
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })
    }
}