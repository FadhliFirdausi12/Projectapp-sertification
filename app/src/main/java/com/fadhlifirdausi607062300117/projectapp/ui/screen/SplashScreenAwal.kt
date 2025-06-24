package com.fadhlifirdausi607062300117.projectapp.ui.screen

import com.fadhlifirdausi607062300117.projectapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.material3.*

@Composable
fun SplashScreenAwal(navController: NavController) {
    LaunchedEffect(true) {
        delay(5000L)
        navController.navigate("login") {
            popUpTo("splash_awal") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.mipmap.mahasiswa_removebg_preview), // logo.png di drawable
                contentDescription = "Logo Aplikasi",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "KAMPUSKU",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
