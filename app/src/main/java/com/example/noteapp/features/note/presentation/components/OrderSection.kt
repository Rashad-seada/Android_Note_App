package com.example.notes.features.note_feature.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes.features.note_feature.domain.util.NoteOrder
import com.example.notes.features.note_feature.domain.util.OrderType

@Composable
fun OrderSection(
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit = {}
) {
    Column(modifier = Modifier.wrapContentSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        ) {
            DefaultRadioButton(
                lable = "Title",
                selected = noteOrder is NoteOrder.Title,
                onChange = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) })
            DefaultRadioButton(
                lable = "Date",
                selected = noteOrder is NoteOrder.Date,
                onChange = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) })
            DefaultRadioButton(
                lable = "Color",
                selected = noteOrder is NoteOrder.Color,
                onChange = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) })
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        ) {
            DefaultRadioButton(
                lable = "Ascending",
                selected = noteOrder.orderType is OrderType.Ascending,
                onChange = { onOrderChange(noteOrder.copy(OrderType.Ascending)) })
            DefaultRadioButton(
                lable = "Descending",
                selected = noteOrder.orderType is OrderType.Descending,
                onChange = { onOrderChange(noteOrder.copy(OrderType.Descending)) })
        }

    }
}