package com.example.noteapp.features.note.presentation.note


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.features.note.domain.models.Note
import com.example.noteapp.features.note.domain.usecase.NoteUsecases
import com.example.notes.features.note_feature.domain.usecase.InvalidNoteException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUsecase: NoteUsecases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val noteTitle = mutableStateOf("")

    val noteContent = mutableStateOf("")
    val noteSelectedColor = mutableStateOf(Note.noteColors.random().toArgb())

    val eventFlow = MutableSharedFlow<UiEvent>()

    var currentNoteId : Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let {
            noteId ->
            if(noteId != -1){
                viewModelScope.launch {
                    noteUsecase.getNoteUsecases(noteId)?.also {
                        note ->
                        currentNoteId = note.id
                        noteTitle.value = note.title
                        noteContent.value = note.content
                        noteSelectedColor.value = note.color
                    }
                }
            }

        }
    }

    fun onEvent(event : AddEditNoteEvent){

        when(event){
            is AddEditNoteEvent.AddNote -> {
                viewModelScope.launch {
                    try {
                        noteUsecase.addNoteUsecase(
                            note = Note(
                                title = noteTitle.value,
                                content = noteContent.value,
                                timeStamp = System.currentTimeMillis().toString(),
                                color = noteSelectedColor.value,
                                id = currentNoteId
                            )
                        )
                        eventFlow.emit(UiEvent.SaveNote)
                    }catch (e: InvalidNoteException){
                        eventFlow.emit(UiEvent.ShowSnakeBar(
                            message = "Unable to save the note"
                        ))

                    }
                }
            }
            is AddEditNoteEvent.ChangeColor -> {
                noteSelectedColor.value = event.color
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnakeBar(val message: String) : UiEvent()

        object SaveNote : UiEvent()
    }
}