package com.example.mynoteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynoteapp.util.DateConverter

@Database(entities = [DbNote::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun noteDao():NoteDatabaseDao
}


