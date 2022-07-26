package ru.cactus.currency.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.cactus.currency.R
import ru.cactus.currency.presentation.theme.DarkGrey100

@Composable
internal fun BottomBar() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(70.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(DarkGrey100)
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            Modifier
                .padding(start = 70.dp)
                .fillMaxHeight()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.home),
                contentDescription = null,
                tint = Color.White
            )
        }
        Surface(Modifier.weight(1f)) {}
        IconButton(
            onClick = { /*TODO*/ },
            Modifier
                .padding(end = 70.dp)
                .fillMaxHeight()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
