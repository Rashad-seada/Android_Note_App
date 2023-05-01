package com.example.notes.features.note_feature.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.features.note.domain.models.Note
import com.example.noteapp.features.note.domain.usecase.NoteUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUsecases: NoteUsecases
) : ViewModel() {

    init {
        noteUsecases.getNotesUsecase()
    }

    val state = mutableStateOf(NotesState())
    private var recentlyDeletedNote: Note? = null
    private var getNotesJob : Job? = null

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.notes::class == event.noteOrder::class
                    && state.value.notesOrder.orderType == event.noteOrder.orderType) {
                    return
                }

                getNotesJob?.cancel()

                getNotesJob = viewModelScope.launch {
                    noteUsecases.getNotesUsecase(event.noteOrder).collectLatest {
                        notes ->
                        state.value = state.value.copy(
                            notes = notes,
                            notesOrder = event.noteOrder
                        )

                    }
                }

            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUsecases.deleteNoteUsecase(event.note)
                    recentlyDeletedNote = event.note
                }
                return
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote?.let { noteUsecases.addNoteUsecase(it) }
                    recentlyDeletedNote = null
                }
                return
            }
            is NotesEvent.ToggleOrderSection -> {
                state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
                return
            }
            is NotesEvent.AddNote -> {
                getNotesJob?.cancel()
                getNotesJob = viewModelScope.launch {
                    noteUsecases.addNoteUsecase(event.note)
                }
            }
            is NotesEvent.GetNotes -> {
                getNotesJob?.cancel()
                getNotesJob = viewModelScope.launch {
                    noteUsecases.getNotesUsecase(state.value.notesOrder).collectLatest {
                            notes ->
                        state.value = state.value.copy(
                            notes = notes,
                            notesOrder = state.value.notesOrder
                        )

                    }
                }
            }
            else -> {}
        }
    }


}