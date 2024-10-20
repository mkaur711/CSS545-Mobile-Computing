package com.example.imagestorageapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.PasswordVisualTransformation

class AuthActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        setContent {
            AuthScreen(sharedPreferences)
        }
    }
}

@Composable
fun AuthScreen(sharedPreferences: SharedPreferences) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignUp by remember { mutableStateOf(true) }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (isSignUp) {
                signUp(username, password, sharedPreferences)
                message = "User registered successfully!"
            } else {
                if (login(username, password, sharedPreferences)) {
                    message = "Login successful!"
                } else {
                    message = "Login failed. Please check your credentials."
                }
            }
        }) {
            Text(text = if (isSignUp) "Sign Up" else "Log In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { isSignUp = !isSignUp }) {
            Text(text = if (isSignUp) "Already have an account? Log In" else "Don't have an account? Sign Up")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = message)
    }
}

fun signUp(username: String, password: String, sharedPreferences: SharedPreferences) {
    val editor = sharedPreferences.edit()
    editor.putString(username, password)
    editor.apply()
}

fun login(username: String, password: String, sharedPreferences: SharedPreferences): Boolean {
    val savedPassword = sharedPreferences.getString(username, null)
    return savedPassword == password
}
