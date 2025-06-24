package com.fadhlifirdausi607062300117.projectapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MahasiswaOptionsDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onDetailClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = "Pilihan") },
            text = {
                Column {
                    Text(
                        text = "Lihat Data",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDetailClick()
                                onDismiss()
                            }
                            .padding(8.dp)
                    )
                    Text(
                        text = "Update Data",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onEditClick()
                                onDismiss()
                            }
                            .padding(8.dp)
                    )
                    Text(
                        text = "Hapus Data",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDeleteClick()
                                onDismiss()
                            }
                            .padding(8.dp)
                    )
                }
            },
            confirmButton = {} // tidak perlu tombol OK/Cancel
        )
    }
}
