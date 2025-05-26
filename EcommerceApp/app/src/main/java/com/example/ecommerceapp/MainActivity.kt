package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceAppTheme {
                var isLoginScreen by remember { mutableStateOf(true) }
                var isAuthenticated by remember { mutableStateOf(false) }

                if (isAuthenticated) {
                    DashboardScreen()
                } else {
                    AuthScreen(
                        isLogin = isLoginScreen,
                        onAuthAction = { email, password ->
                            // TODO: Add real auth logic here
                            isAuthenticated = true
                            println("Email: $email, Password: $password")
                        },
                        onToggleAuthMode = {
                            isLoginScreen = !isLoginScreen
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AuthScreen(
    isLogin: Boolean = true,
    onAuthAction: (String, String) -> Unit,
    onToggleAuthMode: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val title = if (isLogin) "Login" else "Register"
    val buttonText = if (isLogin) "Login" else "Register"
    val toggleText = if (isLogin) "Don't have an account? Register" else "Already have an account? Login"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp),
            tint = Color(0xFF6200EE)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )

        if (!isLogin) {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Button
        Button(
            onClick = { onAuthAction(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = buttonText, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Toggle Auth Text
        TextButton(onClick = onToggleAuthMode) {
            Text(toggleText, color = Color.Gray)
        }
    }
}

data class Product(val name: String, val imageUrl: String, val price: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val products = listOf(
        Product("T-Shirt", "https://images.unsplash.com/photo-1521334884684-d80222895322?auto=format&fit=crop&w=150&q=80", "$20"),
        Product("Shoes", "https://images.pexels.com/photos/19090/pexels-photo.jpg?auto=compress&cs=tinysrgb&h=150", "$40"),
        Product("Jeans", "https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?auto=format&fit=crop&w=150&q=80", "$35"),
        Product("Watch", "https://images.unsplash.com/photo-1518609878373-06d740f60d8b?auto=format&fit=crop&w=150&q=80", "$60"),
    )

    val adImages = listOf(
        "https://images.unsplash.com/photo-1503602642458-232111445657?auto=format&fit=crop&w=800&q=80",
        "https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?auto=format&fit=crop&w=800&q=80",
        "https://images.unsplash.com/photo-1494526585095-c41746248156?auto=format&fit=crop&w=800&q=80"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .padding(paddingValues)
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                // Image slider with subtle shadow and rounded corners
                ImageSlider(
                    images = adImages,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color.White)
                        .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Section header
                Text(
                    text = "Featured Products",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )

                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(products) { product ->
                        ProductCard(product)
                    }
                }
            }
        }
    )
}

@Composable
fun ImageSlider(images: List<String>, modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableIntStateOf(0) }

    // Auto-scroll effect
    LaunchedEffect(currentIndex) {
        kotlinx.coroutines.delay(4000)
        currentIndex = (currentIndex + 1) % images.size
    }

    Box(modifier = modifier) {
        Image(
            painter = rememberAsyncImagePainter(images[currentIndex]),
            contentDescription = "Advertisement Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Dots indicator with better style
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp)
        ) {
            images.forEachIndexed { index, _ ->
                val color = if (index == currentIndex) Color(0xFF6200EE) else Color(0xFFB0BEC5)
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (index == currentIndex) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )
                Text(
                    text = product.price,
                    fontSize = 18.sp,
                    color = Color(0xFF757575)
                )
                Button(
                    onClick = { /* Handle Add to Cart */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add to Cart", color = Color.White)
                }
            }
        }
    }
}
