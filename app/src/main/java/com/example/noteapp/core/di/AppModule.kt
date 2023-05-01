package com.example.noteapp.core.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.features.note.data.repo.NoteRepoImpl
import com.example.noteapp.features.note.domain.repo.NoteRepo
import com.example.noteapp.features.note.domain.usecase.GetNoteUsecase
import com.example.notes.features.note_feature.data.data_source.NoteDatabase

import com.example.notes.features.note_feature.domain.usecase.AddNoteUsecase
import com.example.notes.features.note_feature.domain.usecase.DeleteNoteUsecase
import com.example.notes.features.note_feature.domain.usecase.GetNotesUsecase
import com.example.noteapp.features.note.domain.usecase.NoteUsecases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app : Application) : NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepo(database: NoteDatabase) : NoteRepoImpl {
        return NoteRepoImpl(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideUsecases(repo : NoteRepoImpl) : NoteUsecases {
        return NoteUsecases(
            getNotesUsecase = GetNotesUsecase(repo),
            deleteNoteUsecase = DeleteNoteUsecase(repo),
            addNoteUsecase = AddNoteUsecase(repo),
            getNoteUsecases = GetNoteUsecase(repo)
        )
    }

}