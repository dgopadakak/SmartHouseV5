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
import com.dgopadakak.smarthousev5.model.states.DishwasherState

@Composable
fun DishwasherCard(
    pinned: MutableState<Int>,
    dishwasherState: MutableState<DishwasherState>
) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = if (pinned.value == 2)
                Modifier.fillMaxWidth()
            else
                Modifier
                    .width(300.dp)
                    .height(140.dp)
        ) {
            if (pinned.value != 2) {
                IconButton(
                    onClick = { pinned.value = 2 },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_circle_outline_24),
                        contentDescription = "Add button"
                    )
                }
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