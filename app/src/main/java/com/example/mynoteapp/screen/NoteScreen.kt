package com.example.mynoteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynoteapp.R
import com.example.mynoteapp.components.NoteButton
import com.example.mynoteapp.components.NoteInputText
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.data.NotesDataSource
import java.time.format.DateTimeFormatter


@ExperimentalComposeUiApi
@Composable
fun NoteScreen(

    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val mycontext = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {

        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "appbar icon"
                )
            },
            backgroundColor = Color(0xFFDADFE3)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(text = title,
                label = "title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) {
                        title = it
                    }
                })

            NoteInputText(text = description,
                label = "description",
                onTextChange = {
                    description = it
                })
            NoteButton(
                text = stringResource(id = R.string.button),
                modifier = Modifier.padding(16.dp),
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        Toast.makeText(mycontext, "Note added", Toast.LENGTH_SHORT).show()
                        onAddNote(Note(title = title, description = description))

                        title = ""
                        description = ""

                    }
                })
            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note,
                        onNoteClicked = {onRemoveNote(note)}
                    )

                }
            }
        }


    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(modifier
            .clickable { onNoteClicked(note) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE d MMM")),
                style = MaterialTheme.typography.caption
            )


        }
    }

}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}