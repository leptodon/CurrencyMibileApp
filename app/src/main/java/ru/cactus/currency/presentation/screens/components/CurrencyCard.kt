package ru.cactus.currency.presentation.screens.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.cactus.currency.ui.theme.DarkAlt
import ru.cactus.currency.ui.theme.DarkGrey100

@Composable
internal fun CurrencyCard(
    currency: String = "United States Dollar",
    symbol: String = "USD",
    rate: String = "58.125",
    img: String = "",
    isFavorite: Boolean = false,
    onItemClick: (String, String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = DarkAlt,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight(CenterVertically)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(40.dp)
                    .background(
                        DarkGrey100
                    ),
                verticalArrangement = Arrangement.Center,
            ){
                Text(
                    text = img,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }

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
    img: String = ""
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = DarkAlt,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight(CenterVertically)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(40.dp)
                    .background(
                        DarkGrey100
                    ),
                verticalArrangement = Arrangement.Center,
            ){
                Text(
                    text = img,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(CenterHorizontally)
                )
            }

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