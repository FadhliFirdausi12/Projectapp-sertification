package com.fadhlifirdausi607062300117.projectapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.fadhlifirdausi607062300117.projectapp.R
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.dashboard),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF2E7D32),
                    titleContentColor = Color.White
                ),
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo mahasiswa
            Image(
                painter = painterResource(id = R.mipmap.mahasiswa_removebg_preview), // Ganti sesuai nama file gambar kamu
                contentDescription = "Mahasiswa",
                modifier = Modifier
                    .size(160.dp)
                    .padding(bottom = 32.dp)
            )

            // Tombol Lihat Data
            Button(
                onClick = { navController.navigate("main_screen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)), // merah tua
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("LIHAT DATA", color = Color.White, fontWeight = FontWeight.Bold)
            }

            // Tombol Input Data
            Button(
                onClick = { navController.navigate("detailScreen") /* Navigasi ke halaman input data */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)), // biru
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("INPUT DATA", color = Color.White, fontWeight = FontWeight.Bold)
            }

            // Tombol Informasi
            Button(
                onClick = { navController.navigate("about_screen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFBC02D)), // kuning gelap
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("INFORMASI", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}