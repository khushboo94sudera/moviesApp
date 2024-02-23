package com.example.myapplication.ui.tickets

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.database.entities.MoviesEntity
import com.example.myapplication.database.entities.Ticket
import com.example.myapplication.database.entities.ticketList
import com.example.myapplication.ui.reusable_content.HeadingText
import org.koin.androidx.compose.koinViewModel

@Composable
fun Tickets(
    viewModel: TicketsViewModel = koinViewModel(),
    navigateToTicketDetails:(String)->Unit
) {
    val tickets by viewModel.ticketsState.collectAsState(emptyList())

        Box(
            modifier = Modifier
                //.padding(it)
                .fillMaxSize()
                .background(color = Color.Black),
        ){
            Column(
                modifier = Modifier.padding( horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ){
                Spacer(modifier = Modifier.height(60.dp))
                HeadingText(name = "My Tickets")
                Spacer(modifier = Modifier.height(24.dp))
                MyTicketsList(navigateToTicketDetails, ticketList = tickets)
            }
        }
    }

@Composable
fun MyTicketsList(navigateToTicketDetails:(String)->Unit, ticketList: List<MoviesEntity>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(ticketList){ item->
            TicketCard(myList = item,navigateToTicketDetails)
        }
    }
}

@Composable
fun TicketCard(myList: MoviesEntity, navigateToTicketDetails:(String)->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToTicketDetails(myList.id) },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Row {
            Box(){
                /*Image(
                    painter = painterResource(id = myList.ticketImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 196.dp, width = 140.dp)
                )*/
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/" + myList.posterPath,
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 196.dp, width = 140.dp)
                )
                /*Image(
                    painter = painterResource(id = myList.ticketMaskImage),
                    contentDescription = null,
                    modifier = Modifier.size(height = 196.dp, width = 140.dp)
                )*/
            }
            Column(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = myList.title?:"Title",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Local:",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White
                )
                Text(
                    text = "São Paulo, SP - Allianz Park",
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Date:",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White
                )
                Text(
                    text = "28/09/2023",
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Opening:",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White
                )
                Text(
                    text = "21:00 (BRT)",
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(
                        text = "Purchased on: ",
                        style = MaterialTheme.typography.displaySmall.copy(fontStyle = FontStyle.Italic),
                        color = Color.White
                    )
                    Text(
                        text = "22/05/2023",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
                        color = Color.White
                    )
                }
            }
        }
    }
}