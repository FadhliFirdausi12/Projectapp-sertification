package com.fadhlifirdausi607062300117.projectapp.ui.screen

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fadhlifirdausi607062300117.projectapp.database.MahasiswaDb
import com.fadhlifirdausi607062300117.projectapp.util.ViewModelFactory

const val KEY_ID_MAHASISWA = "idMahasiswa"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    id: Long? = null,
    isReadOnly: Boolean = false
) {
    val context = LocalContext.current
    val db = MahasiswaDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    var nomor by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var kelamin by remember { mutableStateOf("Laki-laki") }
    var alamat by remember { mutableStateOf("") }

    // Kalender
    val calendar = java.util.Calendar.getInstance()
    val year = calendar.get(java.util.Calendar.YEAR)
    val month = calendar.get(java.util.Calendar.MONTH)
    val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)

    // Load data untuk edit atau detail
    LaunchedEffect(id) {
        if (id != null) {
            viewModel.getMahasiswas(id)
        }
    }

    // Isi form kalau data tersedia
    LaunchedEffect(data) {
        data?.let {
            nomor = it.nomor
            nama = it.nama
            tanggalLahir = it.tanggalLahir
            kelamin = it.kelamin
            alamat = it.alamat
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when {
                            id == null -> "Tambah Mahasiswa"
                            isReadOnly -> "Detail Mahasiswa"
                            else -> "Edit Mahasiswa"
                        },
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    if (!isReadOnly) {
                        IconButton(onClick = {
                            if (nomor.isBlank() || nama.isBlank() || tanggalLahir.isBlank() || alamat.isBlank()) {
                                Toast.makeText(context, "Semua data wajib diisi!", Toast.LENGTH_SHORT).show()
                                return@IconButton
                            }

                            if (id == null) {
                                viewModel.insert(nomor, nama, tanggalLahir, kelamin, alamat)
                            } else {
                                viewModel.update(id, nomor, nama, tanggalLahir, kelamin, alamat)
                            }
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Outlined.Check, contentDescription = "Simpan")
                        }

                        if (id != null) {
                            IconButton(onClick = {
                                viewModel.delete(id)
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Filled.Delete, contentDescription = "Hapus")
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2E7D32)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nomor,
                onValueChange = { nomor = it },
                label = { Text("Nomor") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                enabled = !isReadOnly,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                label = { Text("Nama") },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                singleLine = true,
                enabled = !isReadOnly,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tanggalLahir,
                onValueChange = {},
                label = { Text("Tanggal Lahir") },
                readOnly = true,
                enabled = !isReadOnly,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = !isReadOnly) {
                        DatePickerDialog(
                            context,
                            { _, y, m, d -> tanggalLahir = "$d/${m + 1}/$y" },
                            year,
                            month,
                            day
                        ).show()
                    }
            )

            if (!isReadOnly) {
                Text("Jenis Kelamin")
                val opsiKelamin = listOf("Laki-laki", "Perempuan")
                opsiKelamin.forEach { jenis ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (kelamin == jenis),
                                onClick = { kelamin = jenis }
                            )
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (kelamin == jenis),
                            onClick = { kelamin = jenis }
                        )
                        Text(text = jenis)
                    }
                }
            } else {
                Text("Jenis Kelamin: $kelamin")
            }

            OutlinedTextField(
                value = alamat,
                onValueChange = { alamat = it },
                label = { Text("Alamat") },
                enabled = !isReadOnly,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
