package com.fadhlifirdausi607062300117.projectapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fadhlifirdausi607062300117.projectapp.ui.screen.AboutScreen
import com.fadhlifirdausi607062300117.projectapp.ui.screen.DashboardScreen
import com.fadhlifirdausi607062300117.projectapp.ui.screen.DetailScreen
import com.fadhlifirdausi607062300117.projectapp.ui.screen.KEY_ID_MAHASISWA
import com.fadhlifirdausi607062300117.projectapp.ui.screen.LoginScreen
import com.fadhlifirdausi607062300117.projectapp.ui.screen.MainScreen
import com.fadhlifirdausi607062300117.projectapp.ui.screen.RegisterScreen
import com.fadhlifirdausi607062300117.projectapp.ui.screen.SplashLoginBerhasil
import com.fadhlifirdausi607062300117.projectapp.ui.screen.SplashScreenAwal

@Composable
fun SetUpNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.SplashAwal.route) {

        composable(route = Screen.SplashAwal.route) {
            SplashScreenAwal(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Screen.SplashLogin.route) {
            SplashLoginBerhasil(navController = navController)
        }

        composable(route = Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController = navController)
        }

        composable(route = Screen.AboutScreen.route) {
            AboutScreen(navController = navController)
        }


        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_MAHASISWA) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_MAHASISWA)
            DetailScreen(navController, id)
        }

    }
}