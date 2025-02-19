package com.example.paging_app_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.paging_app_sample.ui.PagingApp
import com.example.paging_app_sample.ui.theme.Paging_app_sampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Paging_app_sampleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PagingApp()
                }
            }
        }
    }
}
