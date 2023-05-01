package com.example.noteapp.features.note.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.features.note.presentation.note.AddEditNoteScreen
import com.example.noteapp.features.note.presentation.note.NotesScreen

@Composable
fun Navigation(){
    
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.NotesScreen.route){
        composable(route = Screen.NotesScreen.route){
            NotesScreen(navController = navController)
        }

        composable(
            route = Screen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId",
                ){
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor",
                ){
                    type = NavType.IntType
                    defaultValue = -1
                }


            )
            ){
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(navController = navController, noteColor = color)
        }
    }
}