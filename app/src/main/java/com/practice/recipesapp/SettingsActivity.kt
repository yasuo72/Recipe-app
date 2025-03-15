package com.practice.recipesapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var editTextCurrentPassword: EditText
    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonChangePassword: Button
    private lateinit var switchNotifications: Switch
    private lateinit var spinnerTheme: Spinner
    private lateinit var switchPrivateAccount: Switch
    private lateinit var switchDataUsage: Switch
    private lateinit var buttonLogout: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize UI elements
        editTextCurrentPassword = findViewById(R.id.editTextCurrentPassword)
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        buttonChangePassword = findViewById(R.id.buttonChangePassword)
        switchNotifications = findViewById(R.id.switchNotifications)
        spinnerTheme = findViewById(R.id.spinnerTheme)
        switchPrivateAccount = findViewById(R.id.switchPrivateAccount)
        switchDataUsage = findViewById(R.id.switchDataUsage)
        buttonLogout = findViewById(R.id.buttonLogout)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("User  Settings", MODE_PRIVATE)

        // Load saved settings
        loadSettings()

        // Set up theme options in the spinner
        setupThemeSpinner()

        // Set up button click listeners
        buttonChangePassword.setOnClickListener {
            changePassword()
        }

        buttonLogout.setOnClickListener {
            logout()
        }

        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            saveNotificationPreference(isChecked)
        }

        switchPrivateAccount.setOnCheckedChangeListener { _, isChecked ->
            savePrivacyPreference(isChecked)
        }

        switchDataUsage.setOnCheckedChangeListener { _, isChecked ->
            saveDataUsagePreference(isChecked)
        }
    }

    private fun loadSettings() {
        // Load notification preference
        val notificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", false)
        switchNotifications.isChecked = notificationsEnabled

        // Load privacy preference
        val privateAccountEnabled = sharedPreferences.getBoolean("private_account", false)
        switchPrivateAccount.isChecked = privateAccountEnabled

        // Load data usage preference
        val dataUsageEnabled = sharedPreferences.getBoolean("data_usage_wifi_only", false)
        switchDataUsage.isChecked = dataUsageEnabled
    }

    private fun setupThemeSpinner() {
        // Sample themes
        val themes = arrayOf("Light", "Dark", "System Default")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, themes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTheme.adapter = adapter

        // Load saved theme preference
        val savedTheme = sharedPreferences.getString("selected_theme", "System Default")
        val position = themes.indexOf(savedTheme)
        spinnerTheme.setSelection(if (position >= 0) position else 0)
    }

    private fun changePassword() {
        val currentPassword = editTextCurrentPassword.text.toString()
        val newPassword = editTextNewPassword.text.toString()

        // Validate input
        if (currentPassword.isEmpty() || newPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Here you would typically check the current password against stored data
        // For demonstration, we'll assume the current password is always correct

        // Save the new password (this is just a placeholder)
        Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()

        // Clear the input fields
        editTextCurrentPassword.text.clear()
        editTextNewPassword.text.clear()
    }

    private fun saveNotificationPreference(enabled: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("notifications_enabled", enabled)
        editor.apply()
        Toast.makeText(this, "Notification preference saved", Toast.LENGTH_SHORT).show()
    }

    private fun savePrivacyPreference(enabled: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("private_account", enabled)
        editor.apply()
        Toast.makeText(this, "Privacy preference saved", Toast.LENGTH_SHORT).show()
    }

    private fun saveDataUsagePreference(enabled: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("data_usage_wifi_only", enabled)
        editor.apply()
        Toast.makeText(this, "Data usage preference saved", Toast.LENGTH_SHORT).show()
    }

    private fun logout() {
        // Logic to log out the user (e.g., clear user session)
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        // Redirect to login activity if needed
        // startActivity(Intent(this, LoginActivity::class.java))
        // finish()
    }
}