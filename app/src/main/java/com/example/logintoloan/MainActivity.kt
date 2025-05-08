package com.example.logintoloan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logintoloan.ui.theme.LogintoLoanTheme
import kotlinx.coroutines.delay
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogintoLoanTheme {
                var currentScreen by remember { mutableStateOf("start") }
                
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        when (currentScreen) {
                            "google" -> {
                                CenterAlignedTopAppBar(
                                    title = { },
                                    navigationIcon = {
                                        IconButton(onClick = { currentScreen = "start" }) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    },
                                    actions = {
                                        IconButton(onClick = { /* TODO: Handle notification click */ }) {
                                            Icon(
                                                imageVector = Icons.Default.Notifications,
                                                contentDescription = "Notifications",
                                                tint = Color.Black
                                            )
                                        }
                                    },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = Color.White
                                    )
                                )
                            }
                            "mobile" -> {
                                CenterAlignedTopAppBar(
                                    title = { },
                                    navigationIcon = {
                                        IconButton(onClick = { currentScreen = "start" }) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    },
                                    actions = {
                                        IconButton(onClick = { /* TODO: Handle notification click */ }) {
                                            Icon(
                                                imageVector = Icons.Default.Notifications,
                                                contentDescription = "Notifications",
                                                tint = Color.Black
                                            )
                                        }
                                    },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = Color.White
                                    )
                                )
                            }
                        }
                    },
                    bottomBar = {
                        if (currentScreen == "google") {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                                    )
                                    .padding(horizontal = 16.dp, vertical = 2.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 8.dp, vertical = 2.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Top,
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Home,
                                            contentDescription = "Explore",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Explore",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                    
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Top,
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "Credit Score",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Credit Score",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                    
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Top,
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "My Account",
                                            tint = Color.Black,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "My Account",
                                            color = Color.Black,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    when (currentScreen) {
                        "start" -> {
                            SignInPanel(
                                onMobileSignInClick = { currentScreen = "mobile" },
                                onGoogleSignInClick = { currentScreen = "google" },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        "mobile" -> {
                            MobileLoginPanel(
                                onBackClick = { currentScreen = "start" },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        "google" -> {
                            GoogleSignInPanel(
                                onBackClick = { currentScreen = "start" },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SignInPanel(
    onMobileSignInClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Center Image (Replace R.drawable.logo with your actual image resource)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Single Click Sign-in Text
        Text(
            text = "Single Click Sign-in",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Description Text
        Text(
            text = "Access your credit score, card & loan applications",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Sign in with Google Button
        Button(
            onClick = onGoogleSignInClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Sign in with Google")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // OR Text
        Text(
            text = "OR",
            fontSize = 16.sp,
            color = Color.Gray
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Sign in with Mobile Number Button
        Button(
            onClick = onMobileSignInClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Sign in with Mobile Number")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleSignInPanel(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentImageIndex by remember { mutableStateOf(0) }
    val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 80.dp) // Padding for navigation bar
    ) {
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            
            // Welcome Text
            Text(
                text = "Hey, Welcome to LoginToLoan",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Static Image Display
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFFE0B2))
            ) {
                Image(
                    painter = painterResource(id = images[currentImageIndex]),
                    contentDescription = "Carousel Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentScale = ContentScale.Fit
                )
                
                // Image Indicators
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(images.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    if (index == currentImageIndex) Color.White
                                    else Color.White.copy(alpha = 0.5f)
                                )
                                .clickable {
                                    currentImageIndex = index
                                }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Credit Score Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.White,
                                Color(0xFFFFE0B2) // Light Orange
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left side content
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp)
                    ) {
                        Text(
                            text = "Credit Score",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Check your credit score worth",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Points
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.checkbox_on_background),
                                contentDescription = "Check",
                                modifier = Modifier.size(16.dp),
                                tint = Color.Green
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Track credit loans",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.checkbox_on_background),
                                contentDescription = "Check",
                                modifier = Modifier.size(16.dp),
                                tint = Color.Green
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "No impact on credit score",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Check Button
                        Button(
                            onClick = { /* TODO: Implement check credit score */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF9800) // Orange
                            ),
                            modifier = Modifier
                                .height(40.dp)
                                .width(120.dp)
                        ) {
                            Text(
                                text = "Check",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    
                    // Right side image
                    Image(
                        painter = painterResource(id = R.drawable.logo), // Replace with your credit score image
                        contentDescription = "Credit Score",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            // Explore Text with Lines
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left gradient line
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFF9800), // Orange
                                    Color.White
                                )
                            )
                        )
                )
                
                // Text
                Text(
                    text = "Explore Login to Loan",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                // Right gradient line
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White,
                                    Color(0xFFFF9800) // Orange
                                )
                            )
                        )
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // First Row of Image Frames (with borders)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // First Image Frame
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image1),
                        contentDescription = "Frame 1",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
                
                // Second Image Frame
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image2),
                        contentDescription = "Frame 2",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
                
                // Third Image Frame
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image3),
                        contentDescription = "Frame 3",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
                
                // Fourth Image Frame
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image1),
                        contentDescription = "Frame 4",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
                
                // Fifth Image Frame
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image2),
                        contentDescription = "Frame 5",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Second Row of Image Frames with Labels (without borders)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // First Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image1),
                            contentDescription = "Frame 1",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Loan Against Property",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Second Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image2),
                            contentDescription = "Frame 2",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Credit Card FD",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Third Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image3),
                            contentDescription = "Frame 3",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Loan Against Mutual Fund",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Fourth Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image1),
                            contentDescription = "Frame 4",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Transfer Home Loan",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Fifth Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image2),
                            contentDescription = "Frame 5",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Personal Loan",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Insurance and Investments Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left gradient line
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFF9800), // Orange
                                    Color.White
                                )
                            )
                        )
                )
                
                // Text
                Text(
                    text = "Insurance and Investments",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                // Right gradient line
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White,
                                    Color(0xFFFF9800) // Orange
                                )
                            )
                        )
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Insurance and Investments Image Frames
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // First Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image1),
                            contentDescription = "Term Life",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Term Life",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Second Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image2),
                            contentDescription = "Investment Plan",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Investment Plan",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Third Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image3),
                            contentDescription = "Health Insurance",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Health Insurance",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Fourth Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image1),
                            contentDescription = "Car Insurance",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Car Insurance",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Fifth Image Frame with Label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image2),
                            contentDescription = "Home Insurance",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Home Insurance",
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Recommended Services Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE3F2FD)) // Light blue color
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title with gradient lines
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left gradient line
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(1.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF2196F3), // Blue
                                            Color(0xFFE3F2FD) // Light blue
                                        )
                                    )
                                )
                        )
                        
                        // Text
                        Text(
                            text = "Recommended Services",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        
                        // Right gradient line
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(1.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFFE3F2FD), // Light blue
                                            Color(0xFF2196F3) // Blue
                                        )
                                    )
                                )
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Two Box Frames
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // First Box Frame
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image1),
                                    contentDescription = "Service 1",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .padding(8.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Personal Loan",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        
                        // Second Box Frame
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(12.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.image2),
                                    contentDescription = "Service 2",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .padding(8.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Home Loan",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Tools Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Title with gradient lines
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left gradient line
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF2196F3), // Blue
                                        Color.White
                                    )
                                )
                            )
                    )
                    
                    // Text
                    Text(
                        text = "Tools",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    
                    // Right gradient line
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.White,
                                        Color(0xFF2196F3) // Blue
                                    )
                                )
                            )
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Top Row of Box Frames
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // First Box Frame
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image1),
                                contentDescription = "Tool 1",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "EMI Calculator",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    
                    // Second Box Frame
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image2),
                                contentDescription = "Tool 2",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Loan Eligibility",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Bottom Row of Box Frames
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Third Box Frame
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image3),
                                contentDescription = "Tool 3",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Credit Score",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    
                    // Fourth Box Frame
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image1),
                                contentDescription = "Tool 4",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Loan Comparison",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileLoginPanel(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var mobileNumber by remember { mutableStateOf("") }
    var whatsappUpdates by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        
        // Login with Mobile Number Text
        Text(
            text = "Login with Mobile Number",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Mobile Number Input Field
        OutlinedTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
            label = { Text("Mobile Number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // WhatsApp Updates Toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Get updates on WhatsApp",
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = whatsappUpdates,
                onCheckedChange = { whatsappUpdates = it }
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Send OTP Button
        Button(
            onClick = { /* TODO: Implement OTP sending */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Send OTP")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Privacy Policy Text
        Text(
            text = "By continuing, you have read and agree to the Privacy Policy",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPanelPreview() {
    LogintoLoanTheme {
        SignInPanel(onMobileSignInClick = {}, onGoogleSignInClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun MobileLoginPanelPreview() {
    LogintoLoanTheme {
        MobileLoginPanel(onBackClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun GoogleSignInPanelPreview() {
    LogintoLoanTheme {
        GoogleSignInPanel(onBackClick = {})
    }
}