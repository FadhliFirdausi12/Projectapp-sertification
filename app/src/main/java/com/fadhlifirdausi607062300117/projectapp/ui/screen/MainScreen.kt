package com.fadhlifirdausi607062300117.projectapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fadhlifirdausi607062300117.projectapp.R
import com.fadhlifirdausi607062300117.projectapp.database.MahasiswaDb
import com.fadhlifirdausi607062300117.projectapp.model.DataMahasiswa
import com.fadhlifirdausi607062300117.projectapp.navigation.Screen
import com.fadhlifirdausi607062300117.projectapp.ui.theme.ProjectappTheme
import com.fadhlifirdausi607062300117.projectapp.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.mahasiswa),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E7D32)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.FormBaru.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.tambah_mahasiswa),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    ) { padding ->
        ScreenContent(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val db = MahasiswaDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    var selectedItem by remember { mutableStateOf<DataMahasiswa?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    if (data.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.list_kosong))
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp)
        ) {
            items(data) {
                ListItem(catatan = it, onClick = {
                    selectedItem = it
                    showDialog = true
                })
                Divider()
            }
        }
    }

    // Dialog pilihan
    if (showDialog && selectedItem != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Pilihan") },
            text = {
                Column {
                    Text(
                        "Detail",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screen.FormDetail.withId(selectedItem!!.id))
                                showDialog = false
                            }
                            .padding(8.dp)
                    )
                    Text(
                        "Edit",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screen.FormUbah.withId(selectedItem!!.id))
                                showDialog = false
                            }
                            .padding(8.dp)
                    )
                    Text(
                        "Hapus",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.delete(selectedItem!!.id)
                                showDialog = false
                            }
                            .padding(8.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}

@Composable
fun ListItem(catatan: DataMahasiswa, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = catatan.nomor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = catatan.nama,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = catatan.tanggalLahir)
        Text(text = catatan.kelamin)
        Text(
            text = catatan.alamat,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    ProjectappTheme {
        MainScreen(rememberNavController())
    }
}
