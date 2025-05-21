package com.example.ecommerceapp

import RegisterScreenUI
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterFlow(authViewModel: com.example.ecommerceapp.viewmodel.AuthViewModel = viewModel()) {
    var confirmPassword by remember { mutableStateOf("") }
    var isLoggedIn by remember { mutableStateOf(false) }
    var showLogin by remember { mutableStateOf(false) }

    if (isLoggedIn) {
        Text("Welcome! You are registered.") // You can replace this with HomeScreen later
    } else {
        RegisterScreenUI(
            email = authViewModel.email,
            onEmailChange = { authViewModel.email = it },
            password = authViewModel.password,
            onPasswordChange = { authViewModel.password = it },
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = { confirmPassword = it },
            onRegisterClick = {
                if (authViewModel.password == confirmPassword) {
                    authViewModel.register {
                        isLoggedIn = true
                    }
                } else {
                    authViewModel.errorMessage = "Passwords do not match"
                }
            },
            errorMessage = authViewModel.errorMessage,
            onLoginClick = { showLogin = true },
            isLoading = authViewModel.isLoading,
            onGoogleSignInClick = {
                // TODO: Implement Google Sign-In logic here
            }
        )

    }
}
@Preview(showBackground = true)
@Composable
fun RegisterScreenUIPreview() {
    RegisterScreenUI(
        email = "user@example.com",
        onEmailChange = {},
        password = "password123",
        onPasswordChange = {},
        confirmPassword = "password123",
        onConfirmPasswordChange = {},
        onRegisterClick = {},
        errorMessage = null,
        onLoginClick = {},
        isLoading = false,
        onGoogleSignInClick = {} // Add this line
    )
}

