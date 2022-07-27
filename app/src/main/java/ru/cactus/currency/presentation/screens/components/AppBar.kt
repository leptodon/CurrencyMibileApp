package ru.cactus.currency.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.cactus.currency.R
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.ui.theme.Dark
import ru.cactus.currency.ui.theme.DarkGrey100

@Composable
fun AppBar(symbols: Map<String, String>, viewModel: MainViewModel = hiltViewModel()) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    var filterExpended by remember { mutableStateOf(false) }
    val radioOptions = listOf("Filter by alphabet", "Filter by rate")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    val list = symbols.values.toList()

    if (list.isNotEmpty()) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .background(DarkGrey100)
        ) {
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Dark)
            ) {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .wrapContentSize(Alignment.TopStart),
                    verticalAlignment = CenterVertically,
                ) {
                    Text(
                        list[selectedIndex],
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)
                            .clickable(onClick = { expanded = true }),
                        color = Color.White
                    )

                    IconButton(onClick = { filterExpended = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_filter_list_24),
                            contentDescription = null
                        )
                    }
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        DarkGrey100
                    )
            ) {
                list.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        viewModel.setBaseCurrency(symbols.keys.elementAt(index))
                        expanded = false
                    }) {
                        Text(text = s)
                    }
                }
            }

            DropdownMenu(
                expanded = filterExpended,
                onDismissRequest = { filterExpended = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column {
                        radioOptions.forEach { text ->
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = (text == selectedOption),
                                        onClick = {
                                            filterExpended = false
                                            onOptionSelected(text)
                                        },
                                    )
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = CenterVertically
                            ) {
                                RadioButton(
                                    selected = (text == selectedOption),
                                    modifier = Modifier.padding(8.dp),
                                    onClick = {
                                        filterExpended = false
                                        onOptionSelected(text)
                                    }
                                )
                                Text(
                                    text = text,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}