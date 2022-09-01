package ru.axdar.ui_kit.components.calendar.roll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun Roller(
    years: Array<Int>,
    currentYear: Int,
    months: Array<String>,
    currentMonth: Int,
    onSelectItems: (year: Int, month: Int) -> Unit
) {
    var year by remember {
        mutableStateOf(currentYear)
    }
    var month by remember {
        mutableStateOf(currentMonth)
    }

    Column(
        Modifier
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .width(360.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            RollPicker(
                items = months,
                selectedItem = currentMonth,
                modifier = Modifier
                    .padding(start = 12.dp, top = 24.dp, bottom = 24.dp)
                    .weight(0.5f),
                selectedItemShape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
                onValueChange = { changedMonth ->
                    month = months.indexOf(changedMonth)
                    onSelectItems(years[year], months.indexOf(changedMonth))
                }
            )
            RollPicker(
                items = years,
                selectedItem = currentYear,
                modifier = Modifier
                    .padding(start = 12.dp, top = 24.dp, bottom = 24.dp)
                    .weight(0.5f),
                selectedItemShape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
                onValueChange = { changedYear ->
                    year = years.indexOf(changedYear)
                    onSelectItems(changedYear, month)
                }
            )
        }
    }
}