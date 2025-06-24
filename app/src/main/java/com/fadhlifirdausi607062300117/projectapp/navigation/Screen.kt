package com.fadhlifirdausi607062300117.projectapp.navigation

sealed class Screen(val route: String) {
    data object SplashAwal : Screen("splash_awal")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object SplashLogin : Screen("login_splash")
    data object Dashboard : Screen("dashboard")
    data object MainScreen : Screen("main_screen")
    data object AboutScreen : Screen("about_screen")

    // Route untuk tambah data
    data object FormBaru : Screen("detailScreen")

    // Route untuk ubah data
    data object FormUbah : Screen("detailScreen/{idMahasiswa}") {
        fun withId(id: Long): String = "detailScreen/$id"
    }
}
