package com.example.paging_app_sample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.paging_app_sample.ui.theme.Paging_app_sampleTheme

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier
) {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "RecipesScreen")
        }
    }
}

@Preview
@Composable
fun RecipesScreenPreview() {
    Paging_app_sampleTheme {
        RecipesScreen()
    }
}