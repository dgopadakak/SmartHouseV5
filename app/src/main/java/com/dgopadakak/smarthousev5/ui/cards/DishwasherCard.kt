package com.dgopadakak.smarthousev5.ui.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dgopadakak.smarthousev5.R

@Composable
fun DishwasherCardLarge(/*dishwasherUiState: MutableState<DishwasherUiState>*/) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Coming soon...",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 40.dp)
            )
        }
    }
}

@Composable
fun DishwasherCardSmall(
    pinned: MutableState<Int>,
    /*dishwasherUiState: MutableState<DishwasherUiState>*/
) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(140.dp)
        ) {
            IconButton(
                onClick = { pinned.value = 2 },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_circle_outline_24),
                    contentDescription = "Add button"
                )
            }
            Text(
                text = "Coming soon...",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 40.dp)
            )
        }
    }
}
