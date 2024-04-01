package com.example.myapplication.ui.search_screen_1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.database.entities.GenresEntity
import com.example.myapplication.ui.reusable_content.HeadingText
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = koinViewModel(),
    navigationCallBack:(String, String)->Unit
) {
    val bands by viewModel.genres.collectAsState(emptyList())
    var searchText by remember { mutableStateOf("") }
    SearchScreenWithState(
        bands = bands,
        searchText = searchText,
        onSearch = {searchText = it},
        navigationCallBack = navigationCallBack
    )
}

@Composable
private fun SearchScreenWithState(
    bands: List<GenresEntity>,
    searchText:String,
    onSearch:(String)->Unit,
    navigationCallBack: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            HeadingText(name = "Search")
            Spacer(modifier = Modifier.height(24.dp))
            MySearchBar(
                searchText = searchText,
                onSearch = onSearch
            )
            Spacer(modifier = Modifier.height(40.dp))
            LazySearchCategories(
                bandList = bands.filter {
                    it.name?.contains(searchText, ignoreCase = true) == true
                },
                navigationCallBack = navigationCallBack
            )
        }
    }
}


@Composable
fun LazySearchCategories(
    bandList: List<GenresEntity>,
    navigationCallBack:(String, String)->Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = 24.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        items(bandList) { item ->
            CategoryCard(bandList = item, navigationCallBack = navigationCallBack)
        }
    }
}

@Composable
fun CategoryCard(bandList: GenresEntity, navigationCallBack:(String, String)->Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = {navigationCallBack(bandList.id, bandList.name?:"")}),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.title),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color.Black, Color.Transparent),
                            startX = 10.0f,
                            endX = 300.0f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .align(Alignment.CenterStart),
            ) {
                Text(
                    text = bandList.name ?: "Band_Name",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }


    }
}

@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearch: (String) -> Unit
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = 1.dp, color = Color(0xffA21313)
                ), shape = RoundedCornerShape(8.dp)
            ),

    ) {
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            painter = painterResource(id = R.drawable.vector__5_),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterStart)
        )
            BasicTextField(
                value = searchText,
                onValueChange = { onSearch(it) },
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier
                    .padding(start = 40.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        if(searchText.isEmpty()){
                Text(
                    text = "Artists, Bands or Venues",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xff5E606A),
                    modifier = Modifier
                        .padding(start = 40.dp)
                        .align(Alignment.CenterStart)
                )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    MyApplicationTheme {
        SearchScreenWithState(
            bands = listOf(
                GenresEntity(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()
                ), GenresEntity(
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()
                )
            )
        ) { _, _ ->

        }
    }
}*/