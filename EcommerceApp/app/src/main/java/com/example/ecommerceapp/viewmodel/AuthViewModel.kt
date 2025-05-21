package com.example.ecommerceapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    @OptIn(DelicateCoroutinesApi::class)
    fun register(onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null
        // Simulate Firebase call
        kotlinx.coroutines.GlobalScope.launch {
            kotlinx.coroutines.delay(2000) // fake loading
            if (email.contains("@")) {
                onSuccess()
            } else {
                errorMessage = "Invalid email format"
            }
            isLoading = false
        }
    }
}
