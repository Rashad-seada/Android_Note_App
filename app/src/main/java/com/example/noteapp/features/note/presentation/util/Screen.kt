package com.example.noteapp.features.note.presentation.util

sealed class Screen(val route : String){

    object NotesScreen : Screen("notes_screen")

    object AddEditNoteScreen : Screen("add_edit_note_screen")

    fun withArgs(vararg args : String ) : String {
        return buildString {
            append(route)
            for (arg in args){
                append("/$arg")
            }
        }
    }

}
