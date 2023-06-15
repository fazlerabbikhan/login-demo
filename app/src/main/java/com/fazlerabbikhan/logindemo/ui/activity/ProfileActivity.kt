package com.fazlerabbikhan.logindemo.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.fazlerabbikhan.logindemo.R
import com.fazlerabbikhan.logindemo.data.User
import com.fazlerabbikhan.logindemo.ui.MyApplication
import com.fazlerabbikhan.logindemo.viewmodel.ProfileViewModel
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {
    @Inject
    lateinit var profileViewModel: ProfileViewModel

    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var mobileTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        (applicationContext as MyApplication).appComponent.inject(this)

        // Initialize views
        usernameTextView = findViewById(R.id.profileUsername)
        emailTextView = findViewById(R.id.profileEmail)
        mobileTextView = findViewById(R.id.profileMobile)

        profileViewModel.profileData.observe(this) { user ->
            if (user != null) {
                displayUserData(user)
            }
        }

        profileViewModel.getProfileData()
    }

    private fun displayUserData(user: User) {
        usernameTextView.text = user.username
        emailTextView.text = user.email
        mobileTextView.text = user.mobile
    }
}
