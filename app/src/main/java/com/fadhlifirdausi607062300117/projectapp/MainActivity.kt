package com.fadhlifirdausi607062300117.projectapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fadhlifirdausi607062300117.projectapp.navigation.SetUpNavGraph
import com.fadhlifirdausi607062300117.projectapp.ui.theme.ProjectappTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectappTheme  { // Ganti dengan tema kamu
                SetUpNavGraph()
            }
        }
    }
}