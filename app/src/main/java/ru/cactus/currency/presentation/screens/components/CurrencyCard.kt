package ru.cactus.currency.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cactus.currency.R
import ru.cactus.currency.ui.theme.DarkAlt

@Composable
internal fun CurrencyCard(
    currency: String = "United States Dollar",
    symbol: String = "USD",
    rate: String = "58.125",
    isFavorite: Boolean = false,
    onItemClick: (String, String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = DarkAlt,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight(Alignment.CenterVertically)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = currency, color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = symbol, color = Color.White, fontSize = 10.sp)
            }

            Text(
                modifier = Modifier.padding(end = 20.dp),
                text = rate,
                color = Color.White,
                fontSize = 12.sp
            )

            IconButton(
                onClick = { onItemClick(symbol, currency) },
            ) {
                Icon(
                    Icons.Outlined.Star,
                    tint = if (isFavorite) Color.Yellow else Color.White,
                    contentDescription = null
                )
            }

        }
    }
}

@Composable
internal fun CurrencyCard(
    currency: String = "United States Dollar",
    symbol: String = "USD",
    rate: String = "58.125",
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = DarkAlt,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight(Alignment.CenterVertically)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = currency, color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = symbol, color = Color.White, fontSize = 10.sp)
            }

            Text(
                modifier = Modifier.padding(end = 20.dp),
                text = rate,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}