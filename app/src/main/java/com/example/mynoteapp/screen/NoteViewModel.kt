package com.example.mynoteapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynoteapp.data.NoteMapper
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    private val noteMapper: NoteMapper
) : ViewModel() {

    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    var noteList = _noteList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isNullOrEmpty()) {
                    Log.d("Test", ":empty list ")
                    _noteList.value = emptyList()
                } else {
                    _noteList.value = noteMapper.mapListDBNoteToListNote(listOfNotes)
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)

    }

    fun update(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

}