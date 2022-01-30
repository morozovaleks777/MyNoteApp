package com.example.mynoteapp.repository

import com.example.mynoteapp.data.NoteMapper
import com.example.mynoteapp.data.database.DbNote
import com.example.mynoteapp.data.database.NoteDatabaseDao
import com.example.mynoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val databaseDao: NoteDatabaseDao,
    private val noteMapper: NoteMapper
) {
suspend fun addNote(note: Note)= databaseDao.insert(noteMapper.noteToDbNote(note))
suspend fun updateNote(note: Note)= databaseDao.update(noteMapper.noteToDbNote(note))
suspend fun deleteNote(note: Note)= databaseDao.deleteDbNote(noteMapper.noteToDbNote(note))
suspend fun deleteAllNotes()= databaseDao.deleteAll()
        fun getAllNotes(): Flow<List<DbNote>> = databaseDao.getDbNotes().flowOn(Dispatchers.IO)
        .conflate()


         fun getNote(note: Note):Flow<List<DbNote>>{

         return    databaseDao.getDbNotes().flowOn(Dispatchers.IO)
    .conflate()
         }
}