package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.database.entities.MyTicketsEntity

@Dao
interface MyTicketsDao {
    @Insert
    suspend fun insertTicket(movieId: MyTicketsEntity): Long

    @Query("SELECT * FROM myTicketsTable WHERE movieId = :movieId")
    suspend fun getTicketDao(movieId: String): MyTicketsEntity?

}