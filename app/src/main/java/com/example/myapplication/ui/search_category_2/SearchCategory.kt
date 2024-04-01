package com.example.myapplication.ui.search_category_2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.database.entities.MoviesByGenresEntity
import com.example.myapplication.ui.reusable_content.HeadingText
import com.example.myapplication.ui.reusable_content.IconBack
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchCategory(
    viewModel: SearchCategoryViewModel = koinViewModel(),
    navigationCallBack:(String)-> Unit,
    navigationCallBackForIconBack:()->Unit
) {
    val genreName by viewModel.genresNameState.collectAsState(null)
    val moviesList by viewModel.moviesListByIdState.collectAsState(emptyList())
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.Black)
        ){
            Column(
                modifier = Modifier.padding( start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Row {
                    IconBack(navigationCallback = navigationCallBackForIconBack)
                    Spacer(modifier = Modifier.width(24.dp))
                    HeadingText(name = genreName?:"")
                }
                Spacer(modifier = Modifier.height(24.dp))
                SubCategoryGrid(list = moviesList, navigationCallBack)
            }
        }
    }
}

@Composable
fun SubCategoryGrid(list:List<MoviesByGenresEntity>, navigationCallBack:(String)->Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) { item ->
            GridCardContent( gridCardList = item, navigationCallBack)
        }
    }
}
@Composable
fun GridCardContent(gridCardList: MoviesByGenresEntity, navigationCallBack:(String)->Unit) {
    Card(
        modifier = Modifier
            .clickable(onClick = {navigationCallBack(gridCardList.movieId?:"")})
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ){
        Column {

            AsyncImage(
                model =  "https://image.tmdb.org/t/p/w500/"+ gridCardList.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(width = 148.dp, height = 164.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = gridCardList.title?: "Movie Title",
                style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic),
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )


            Text(
                text = "24/08/2023",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
            )


            Text(
                text = "São Paulo, SP",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
            )

        }
    }
}

@Preview
@Composable
fun PreviewSearchCategory() {
    MyApplicationTheme {
        //SearchCategory(navigationCallBack = {})
    }
}