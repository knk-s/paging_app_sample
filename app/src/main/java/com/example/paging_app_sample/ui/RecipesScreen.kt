package com.example.paging_app_sample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.paging_app_sample.model.Recipe
import com.example.paging_app_sample.ui.theme.Paging_app_sampleTheme

@Composable
fun RecipesScreen(
    uiState: UiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.Loading -> LoadingScreen(modifier.fillMaxSize())
        is UiState.Error -> ErrorScreen(modifier.fillMaxSize())
        is UiState.Success -> ResultScreen(
            uiState.recipes, modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Surface {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(text = "LoadingScreen")

        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Surface {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(text = "ErrorScreen")
        }
    }
}

@Composable
fun ResultScreen(recipes: List<Recipe>, modifier: Modifier = Modifier) {
    Surface {
        LazyColumn(
            modifier = modifier
        ) {
            items(recipes) { recipe ->
                Text(text = recipe.id)
                Text(text = recipe.title)
                Text(text = recipe.url)
                Photo(url = recipe.imageUrl)
                Text(text = recipe.description)
            }
        }
    }
}

@Composable
fun Photo(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(url).build(),
        contentDescription = "Recipe photo",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f / 1f)
    )
}

@Preview
@Composable
fun LoadingScreenPreview() {
    Paging_app_sampleTheme {
        LoadingScreen()
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    Paging_app_sampleTheme {
        ErrorScreen()
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    val sampleRecipes = listOf(
        Recipe(
            id = "0001",
            title = "レシピ1",
            url = "recipe1.com",
            imageUrl = "recipe1-image.com",
            description = "レシピ1の説明です。"
        ),
        Recipe(
            id = "0002",
            title = "レシピ2",
            url = "recipe2.com",
            imageUrl = "recipe2-image.com",
            description = "レシピ2の説明です。"
        )
    )

    Paging_app_sampleTheme {
        ResultScreen(recipes = sampleRecipes)
    }
}