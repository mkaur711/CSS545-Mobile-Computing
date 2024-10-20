package com.example.imagestorageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagestorageapp.ui.theme.ImageStorageAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // State variable to manage dark mode
            var isDarkMode by remember { mutableStateOf(false) }
            // State variable to manage user authentication
            var isSignedIn by remember { mutableStateOf(false) }
            // State variables for username and password
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            // Setting up the theme based on the user's preference
            ImageStorageAppTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (isSignedIn) {
                            // User is signed in, display the main app content
                            DisplayImage()
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { isSignedIn = false }) {
                                Text(text = "Sign Out")
                            }
                        } else {
                            // User is not signed in, display sign-in/sign-up form
                            SignInSignUpForm(
                                username = username,
                                password = password,
                                onUsernameChange = { username = it },
                                onPasswordChange = { password = it },
                                onSignIn = {
                                    // Handle sign-in logic (for now, just check if fields are filled)
                                    if (username.isNotEmpty() && password.isNotEmpty()) {
                                        isSignedIn = true // Simulate sign-in success
                                    }
                                },
                                onSignUp = {
                                    // Handle sign-up logic (for now, just check if fields are filled)
                                    if (username.isNotEmpty() && password.isNotEmpty()) {
                                        isSignedIn = true // Simulate sign-up success
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Button to toggle between Dark and Light mode
                        Button(onClick = {
                            isDarkMode = !isDarkMode // Toggle dark mode
                        }) {
                            Text(text = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayImage() {
    // Display a local image from drawable resources
    Image(
        painter = painterResource(id = R.drawable.image),
        contentDescription = "Stored Image",
        modifier = Modifier.size(200.dp)
    )
}

@Composable
fun SignInSignUpForm(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        TextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation() // Hides password input
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onSignIn) {
                Text("Sign In")
            }
            Button(onClick = onSignUp) {
                Text("Sign Up")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageStorageAppTheme {
        DisplayImage()
    }
}
