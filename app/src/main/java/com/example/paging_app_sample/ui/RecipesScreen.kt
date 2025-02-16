package com.example.paging_app_sample.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.paging_app_sample.R
import com.example.paging_app_sample.model.Recipe
import com.example.paging_app_sample.ui.theme.Dimensions
import com.example.paging_app_sample.ui.theme.Paging_app_sampleTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun RecipesScreen(
    viewModel: RecipesViewModel,
    modifier: Modifier = Modifier
) {
    val response = viewModel.recipes.collectAsLazyPagingItems()

    when (response.loadState.refresh) {
        is LoadState.Loading -> LoadingScreen(modifier)
        is LoadState.Error -> ErrorScreen({ response.retry() }, modifier)
        else -> ResultScreen(response, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Surface {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = modifier.size(Dimensions.indicatorSize)
            )
        }
    }
}

@Composable
fun ErrorScreen(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.error_message))
        Button(onClick = onClick, modifier = Modifier.padding(Dimensions.paddingMedium)) {
            Text(text = stringResource(R.string.retry_button))
        }
    }
}

@Composable
fun ResultScreen(recipes: LazyPagingItems<Recipe>, modifier: Modifier = Modifier) {
    Surface {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxSize()
        ) {
            items(recipes.itemCount) { index ->
                recipes[index]?.let { recipe ->
                    Card(
                        modifier = Modifier
                            .padding(Dimensions.paddingSmall)
                            .height(Dimensions.cardHeight),
                        shape = RectangleShape
                    ) {
                        Column(modifier = Modifier.padding(Dimensions.paddingMedium)) {
                            Photo(url = recipe.imageUrl)
                            Title(title = recipe.title)
                            Description(description = recipe.description)
                            Link(url = recipe.url)
                        }
                    }
                }
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

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.W600,
        modifier = Modifier.padding(vertical = Dimensions.paddingLarge)
    )
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        maxLines = 6,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
// リンクは外部ブラウザで開く
fun Link(url: String) {
    val uriHandler = LocalUriHandler.current

    Text(
        text = stringResource(R.string.recipe_link),
        color = MaterialTheme.colorScheme.tertiary,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = Dimensions.paddingMedium)
            .clickable(
                onClickLabel = stringResource(R.string.recipe_link)
            ) {
                uriHandler.openUri(url)
            }
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
        ErrorScreen(onClick = {})
    }
}

@Preview
@Composable
fun ResultScreenPreview() {
    val sampleRecipes = PagingData.from(
        listOf(
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
    )
    val flow = flowOf(sampleRecipes)
    val items = flow.collectAsLazyPagingItems()

    Paging_app_sampleTheme {
        ResultScreen(recipes = items)
    }
}