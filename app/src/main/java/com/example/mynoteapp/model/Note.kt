package com.example.mynoteapp.model

import java.time.Instant
import java.time.LocalDateTime
import java.util.*

data class Note(
    val id:UUID=UUID.randomUUID(),
    val title:String,
    val description:String,
  //  val entryDate: String= LocalDateTime.now().toString()
    val entryDate: Date = Date.from(Instant.now())
)
