package com.example.noteapp.features.note.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: String,
    val color: Int,
    @PrimaryKey val id: Int? = null,
) {

    companion object {
        val noteColors = listOf(
            greenColor,
            orangeColor,
            redColor,
            lightGreenColor,
            blueColor,
        )
    }


}