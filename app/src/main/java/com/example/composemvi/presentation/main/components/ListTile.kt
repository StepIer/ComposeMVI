package com.example.composemvi.presentation.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListTile(
    textId: String,
    textEvent: String,
    textTime: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .fillMaxWidth()
            .heightIn(64.dp, 200.dp),
        onClick = { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = textId,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = textEvent,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(4f)
            )
            Text(
                text = textTime,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(2f)
            )
        }
    }
}