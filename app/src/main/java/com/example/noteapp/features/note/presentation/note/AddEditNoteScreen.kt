package com.example.noteapp.features.note.presentation.note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapp.features.note.domain.models.Note
import com.example.noteapp.features.note.presentation.components.CustomTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    var titleState = viewModel.noteTitle
    var contentState = viewModel.noteContent

    val snackBarHostState = remember { SnackbarHostState() }
    val noteBackgroundColor = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteSelectedColor.value)
        )
    }
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnakeBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message,
                    )
                }
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.popBackStack()
                }
            }

        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.AddNote)
                },
                containerColor = MaterialTheme.colorScheme.primary,

                ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "save my note")
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundColor.value)
                .padding(16.dp)
        ) {
            
            Spacer(modifier = Modifier.height(15.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(Note.noteColors) {

                    Surface(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                scope.launch {
                                    noteBackgroundColor.animateTo(
                                        targetValue = Color(it.toArgb()),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }

                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(it.toArgb()))
                            },
                        color = it,
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 3.dp,
                            color = if (viewModel.noteSelectedColor.value == it.toArgb()) Color.Black else Color.Transparent
                        ),
                        shadowElevation = 5.dp
                    ) {

                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                modifier = Modifier.height(65.dp),
                value = titleState.value,
                onValueChange = {
                    titleState.value = it
                    println(">>>>>>>>>>>. ${titleState}")
                },
                maxLines = 2,
                hint = "Enter Title",
                fontSize = 30.sp
            )

            CustomTextField(
                modifier = Modifier.fillMaxHeight(),
                value = contentState.value,
                onValueChange = {
                    contentState.value = it
                    println(">>>>>>>>>>>. ${contentState}")

                },
                maxLines = 10,
                hint = "Enter your note",
                fontSize = 15.sp

            )


        }
    }

}