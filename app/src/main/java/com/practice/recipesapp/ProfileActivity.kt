package com.practice.recipesapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLogout: Button
    private lateinit var buttonChangePicture: Button
    private lateinit var imageViewProfile: ImageView

    private val REQUEST_IMAGE_PICK = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLogout = findViewById(R.id.buttonLogout)
        buttonChangePicture = findViewById(R.id.buttonChangePicture)
        imageViewProfile = findViewById(R.id.imageViewProfile)

        // Set up button click listeners
        buttonSave.setOnClickListener {
            saveProfileChanges()
        }

        buttonLogout.setOnClickListener {
            logout()
        }

        buttonChangePicture.setOnClickListener {
            changeProfilePicture()
        }
    }

    private fun saveProfileChanges() {
        // Logic to save changes (e.g., update user data in the database)
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextPhone.text.toString()
        val password = editTextPassword.text.toString()

        // Add validation as needed
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Save the data (this is just a placeholder)
        // You would typically save this data to a database or shared preferences
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
    }

    private fun logout() {
        // Logic to log out the user (e.g., clear user session)
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        // Redirect to login activity if needed
        // startActivity(Intent(this, LoginActivity::class.java))
        // finish()
    }

    private fun changeProfilePicture() {
        // Open the gallery to select an image
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                updateProfilePicture(uri)
            }
        }
    }

    private fun updateProfilePicture(imageUri: Uri) {
        imageViewProfile.setImageURI(imageUri)
        // Additional logic to save the image URI to the user's profile can be added here
    }
}