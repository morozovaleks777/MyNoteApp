package com.example.mynoteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mynoteapp.data.NotesDataSource
import com.example.mynoteapp.model.Note

class NoteViewModel:ViewModel() {
    var noteList= mutableStateListOf<Note>()


    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note:Note){
        noteList.add(note)
    }
    fun removeNote(note: Note){
        noteList.remove(note)
    }
    fun getAllNotes():List<Note>{
        return noteList
    }
}