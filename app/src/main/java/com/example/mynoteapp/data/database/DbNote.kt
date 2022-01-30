package com.example.mynoteapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "notes_tbl")
data class DbNote(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val title:String,
    val description:String,
   // val entryDate: String= LocalDateTime.now().toString()
    val entryDate: Date=  Date.from(Instant.now())
)
