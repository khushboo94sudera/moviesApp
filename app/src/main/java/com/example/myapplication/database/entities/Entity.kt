package com.example.myapplication.database.entities

import com.example.myapplication.R

data class CarouselContentList(
    val movieImage: Int,
    val movieName: String,
    val date: String,
    val address: String
)
val carouselList = listOf(
    CarouselContentList(
        movieImage = R.drawable.logos_facebook,
        movieName = "Bruno Mars",
        date = "24/08/2022",
        address = "São Paulo, SP"
    ),
    CarouselContentList(
        R.drawable.logos_google_icon,
        "Foo Fighters",
        "09/09/2022",
        "Curitiba, PR"
    ),
    CarouselContentList(
        R.drawable.home,
        "Dua Lipa",
        "10/09/2022",
        "São Paulo, SP"
    ),
    CarouselContentList(
        R.drawable.search,
        "Metallica",
        "15/09/2022",
        "Rio de Janeiro, RJ"
    )

)

data class SearchBandCategories(
    val bandName:String,
    val bandImage: Int,
    val selectedBandList: List<HorizontalCardContentList>
)

data class HorizontalCardContentList(
    val movieImage: Int,
    val movieName: String,
    val date: String,
    val address: String
)

val rockList = listOf(
    HorizontalCardContentList(
        R.drawable.logos_facebook,
        "Green Day",
        "12/12/2023",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.logos_google_icon,
        "Red Hot Chilli Peppers",
        "27/12/2023",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.search,
        "Imagine Dragons",
        "07/02/2024",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.profile,
        "Cage the elephant",
        "10/02/2024",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.home,
        "Audio Slave",
        "26/12/2023",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.ticket,
        "Audio Slave",
        "26/12/2023",
        "São Paulo, SP"
    )
)
val countrySide = listOf(
    HorizontalCardContentList(
        R.drawable.logos_facebook,
        "Kendrick Lamar",
        "03/01/2024",
        "Rio de Janeiro, RJ"
    )
)
val pop = listOf(
    HorizontalCardContentList(
        R.drawable.logos_google_icon,
        "Kendrick Lamar",
        "03/01/2024",
        "Rio de Janeiro, RJ"
    ),
    HorizontalCardContentList(
        R.drawable.search,
        "Kendrick Lamar",
        "03/01/2024",
        "Rio de Janeiro, RJ"
    ),
    HorizontalCardContentList(
        R.drawable.profile,
        "Kendrick Lamar",
        "03/01/2024",
        "Rio de Janeiro, RJ"
    )
)
val funk = listOf(
    HorizontalCardContentList(
        R.drawable.ticket,
        "Green Day",
        "12/12/2023",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.home,
        "Green Day",
        "12/12/2023",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.logos_facebook,
        "Green Day",
        "12/12/2023",
        "São Paulo, SP"
    )
)
val rap = listOf(
    HorizontalCardContentList(
        R.drawable.logos_google_icon,
        "Audio Slave",
        "26/12/2023",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.search,
        "Audio Slave",
        "26/12/2023",
        "São Paulo, SP"
    ),
    HorizontalCardContentList(
        R.drawable.profile,
        "Audio Slave",
        "26/12/2023",
        "São Paulo, SP"
    )
)
val electronics = listOf(
    HorizontalCardContentList(
        R.drawable.ticket,
        "Kendrick Lamar",
        "03/01/2024",
        "Rio de Janeiro, RJ"
    ),
    HorizontalCardContentList(
        R.drawable.home,
        "Kendrick Lamar",
        "03/01/2024",
        "Rio de Janeiro, RJ"
    ),
    HorizontalCardContentList(
        R.drawable.logos_facebook,
        "Kendrick Lamar",
        "03/01/2024",
        "Rio de Janeiro, RJ"
    )
)
val bandList = listOf(
    SearchBandCategories(
        bandName = "Countryside",
        bandImage = R.drawable.logos_facebook,
        selectedBandList = countrySide
    ),
    SearchBandCategories(
        bandName = "Rock",
        bandImage = R.drawable.logos_google_icon,
        selectedBandList = rockList
    ),
    SearchBandCategories(
        bandName = "Pop",
        bandImage = R.drawable.search,
        selectedBandList = pop
    ),
    SearchBandCategories(
        bandName = "Funk",
        bandImage = R.drawable.profile,
        selectedBandList = funk
    ),
    SearchBandCategories(
        bandName = "Rap",
        bandImage = R.drawable.ticket,
        selectedBandList = rap
    ),
    SearchBandCategories(
        bandName = "Electronics",
        bandImage = R.drawable.home,
        selectedBandList = electronics
    ),
)
data class Ticket(
    val ticketId:Int,
    val ticketImage : Int,
    val ticketMaskImage : Int,
    val ticketName:String,
    val venue: String,
    val showDate: String,
    val time: String,
    val ticketPurchasedOn: String,
)
val ticketList = listOf(
    Ticket(
        1,
        R.drawable.logos_facebook,
        R.drawable.search,
        "Bad Bunny",
        "São Paulo, SP - Allianz Park",
        "28/09/2023",
        "21:00 (BRT)",
        "22/05/2023"
    ),
    Ticket(
        2,
        R.drawable.logos_google_icon,
        R.drawable.search,
        "Gorillaz",
        "Curitiba, PR - Pedreira Paulo Leminski",
        "17/10/2023",
        "19:00 (BRT)",
        "14/07/2023"
    ),
    Ticket(
        3,
        R.drawable.logos_facebook,
        R.drawable.search,
        "Foo Fighters",
        "São Paulo, SP - Alianz Park",
        "28/10/2023",
        "21:00 (BRT)",
        "13/08/2023"
    ),
)