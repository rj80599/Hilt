package com.example.mvvmhilt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmhilt.ui.theme.MVVMHiltTheme
import com.example.mvvmhilt.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

// hilt example with mvvm pattern with remote and local dataBase connection with repository

// MainActivity <--- ViewModel <--- Repository <--- 1. Retrofit 2. RoomDB
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainViewModel =
            ViewModelProvider(this)[MainViewModel::class.java] // here pass the viewmodel which object is required

        setContent {
            MVVMHiltTheme {
                ProductScreen(mainViewModel = mainViewModel)
            }
        }
    }
}