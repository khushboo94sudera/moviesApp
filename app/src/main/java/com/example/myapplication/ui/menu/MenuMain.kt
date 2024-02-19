package com.example.myapplication.ui.menu

import android.widget.ScrollView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.database.entities.MoviesEntity
import com.example.myapplication.ui.reusable_content.HeadingText
import com.example.myapplication.ui.reusable_content.MyBottomNavigationBar
import com.example.myapplication.ui.reusable_content.SmallHeadingText
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuMain(
    viewModel:MenuViewModel = koinViewModel(),
    navigationCallback:(String)->Unit
) {
    val movies by viewModel.movies.collectAsState(emptyList())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState())
    ){
        Column {
            Column(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                HeadingText(name = "Menu")
                Spacer(modifier = Modifier.height(16.dp))
                SmallHeadingText(name = "Highlighted")
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalCarousel(movieList = movies, navigationCallback = navigationCallback)
                Spacer(modifier = Modifier.height(8.dp))
                SmallHeadingText(name = "Other Shows")
            }
            Column (
                modifier = Modifier.padding( start = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ){
                Spacer(modifier = Modifier.height(8.dp))
                OtherShowCard(movieList = movies, navigationCallback = navigationCallback)
            }
        }
    }
}
@Composable
fun OtherShowCard(movieList: List<MoviesEntity>, navigationCallback:(String)->Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(space = 18.dp),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp)
    ) {
        items(movieList){ item ->
            CardContent(cardList = item, navigationCallback)
        }
    }
}
@Composable
fun CardContent(cardList: MoviesEntity, navigationCallback:(String)->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigationCallback(cardList.id) },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ){
        Column {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/"+ cardList.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(width = 148.dp, height = 164.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = cardList.title ?: "TITLE",
                style = MaterialTheme.typography.titleMedium.copy(fontStyle = FontStyle.Italic),
                modifier = Modifier
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCarousel(movieList: List<MoviesEntity>, navigationCallback:(String)->Unit) {
    val pagerState = rememberPagerState(pageCount = { movieList.size})
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(275.dp)
    ){
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill
        ){index->
            Box(
                modifier = Modifier

                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                )
            ){
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/"+ movieList[index].posterPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

            }

        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color(0xffA21313) else Color.DarkGray
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}
