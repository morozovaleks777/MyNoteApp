package com.example.mynoteapp.data

import com.example.mynoteapp.data.database.DbNote
import com.example.mynoteapp.model.Note
import javax.inject.Inject

class NoteMapper @Inject constructor(

){


    fun noteToDbNote(note:Note):DbNote{
        return DbNote(
            id = note.id,
            title = note.title,
            description = note.description,
            entryDate = note.entryDate
        )
    }

    fun dbNoteToNote(dbNote:DbNote):Note{
        return Note(
            id = dbNote.id,
            title = dbNote.title,
            description = dbNote.description,
            entryDate =dbNote.entryDate

        )
    }

    fun mapListDBNoteToListNote(list: List<DbNote>) =
        list.map {
           dbNoteToNote(it)
        }

}