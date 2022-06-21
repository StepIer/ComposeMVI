package com.example.composemvi.presentation.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemvi.presentation.model.Event

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventTile(
    event: Event,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxSize()
            .heightIn(64.dp, 200.dp),
        onClick = { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = event.event,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}