package com.example.composemvi.presentation.main.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composemvi.presentation.model.Event
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyColumnSwipeToDismiss(
    events: List<Event> = emptyList(),
    lazyListState: LazyListState = rememberLazyListState(),
    onDelete: (id: Int) -> Unit = {},
    onClick: (id: Int) -> Unit = {},
    content: LazyListScope.() -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier.heightIn(max = (LocalConfiguration.current.screenHeightDp - (64 + 64 + 80)).dp),
        state = lazyListState
    ) {
        items(events, { event: Event -> event.id!! }) { item ->
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                onDelete(item.id!!)
            }
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier
                    .padding(vertical = Dp(1f)),
                directions = setOf(
                    DismissDirection.EndToStart
                ),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                },
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color.Red
                        }
                    )
                    val alignment = Alignment.CenterEnd
                    val icon = Icons.Default.Delete

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = Dp(20f)),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                dismissContent = {

                    Card(
                        elevation = animateDpAsState(
                            if (dismissState.dismissDirection != null) 4.dp else 0.dp
                        ).value,
                        modifier = Modifier
                            .fillMaxSize()
//                            .fillMaxWidth()
//                            .height(Dp(50f))
                            .align(alignment = Alignment.CenterVertically)
//                            .padding(8.dp)
                    ) {
                        ListTile(
                            textId = item.id.toString(),
                            textEvent = item.event,
                            textTime = item.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        ) {
                            onClick(item.id!!)
                        }
                    }
                }
            )
        }
        content()
    }
}