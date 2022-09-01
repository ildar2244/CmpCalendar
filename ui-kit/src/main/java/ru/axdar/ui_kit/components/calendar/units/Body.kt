package ru.axdar.ui_kit.components.calendar.units

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.axdar.ui_kit.R
import ru.axdar.ui_kit.components.calendar.dayInRange
import ru.axdar.ui_kit.components.calendar.dayIsMarked
import ru.axdar.ui_kit.components.calendar.isWeekendDay
import ru.axdar.ui_kit.components.calendar.model.CalendarMonth
import ru.axdar.ui_kit.components.calendar.toFormattedString
import ru.axdar.ui_kit.core.theme.CmpAppTheme

@Composable
internal fun CalendarBody(
    month: CalendarMonth,
    from: Long,
    to: Long,
    onDayClicked: (Long) -> Unit
) {
    Column(
        Modifier
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    ) {
        DaysOfWeek()
        month.weeks.value.forEach { week ->
            Week(
                from = from,
                to = to,
                week = week,
                current = month.currentDay,
                onDayClicked = { day -> onDayClicked(day) }
            )
        }
    }
}

@Composable
private fun DaysOfWeek(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(bottom = 8.dp)
            .height(height = 16.dp)
    ) {
        for (day in stringArrayResource(id = R.array.days_of_week)) {
            Day(
                name = day,
                modifier = Modifier.weight(0.14f)
            )
        }
    }
}

@Composable
private fun Week(
    from: Long,
    to: Long,
    week: List<Long>,
    current: Int,
    onDayClicked: (Long) -> Unit
) {
    Row(
        modifier = Modifier.height(height = 38.dp)
    ) {
        week.forEachIndexed { index, day ->
            val inRange = dayInRange(day, from, to)
            val isMarked = dayIsMarked(day, from, to)
            val backgroundShape = getShape(
                isMarked = isMarked,
                inRange = inRange,
                day = day,
                from = from,
                to = to,
                indexInWeek = index
            )
            val textColor = when {
                inRange -> CmpAppTheme.colors.brandCmpRed
                isMarked -> CmpAppTheme.colors.textInverted
                isWeekendDay(index) -> CmpAppTheme.colors.textTertiary
                else -> CmpAppTheme.colors.textPrimary
            }
            val backgroundColor = when {
                inRange -> CmpAppTheme.colors.backgroundSecondary
                isMarked -> CmpAppTheme.colors.backgroundSecondary
                else -> Color.Transparent
            }
            val dayNumber = day.toFormattedString("d")
            Day(
                isMarked = isMarked,
                dayNumber = dayNumber,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp)
                    .weight(0.14f),
                onDayClicked = { onDayClicked(day) },
                textColor = textColor,
                backgroundShape = backgroundShape,
                backgroundColor = backgroundColor,
            )
        }
    }
}

@Composable
private fun Day(
    name: String,
    modifier: Modifier
) {
    DayContainer(modifier = modifier) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            style = CmpAppTheme.typography.p3MediumUpperCase,
            color = CmpAppTheme.colors.textSecondary,
        )
    }
}

@Composable
private fun Day(
    isMarked: Boolean,
    dayNumber: String,
    modifier: Modifier,
    onDayClicked: () -> Unit,
    textColor: Color,
    backgroundShape: Shape,
    backgroundColor: Color
) {
    DayContainer(
        modifier = modifier,
        backgroundColor = backgroundColor,
        shape = backgroundShape
    ) {
        DayStatusContainer(isMarked = isMarked) {
            Text(
                text = dayNumber,
                textAlign = TextAlign.Center,
                color = textColor,
                style = CmpAppTheme.typography.h3Regular,
                modifier = Modifier
                    .padding(start = 4.dp, top = 6.dp, bottom = 6.dp, end = 4.14.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        onClick = onDayClicked
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DayContainer(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = backgroundColor,
        shape = shape
    ) {
        content()
    }
}

@Composable
private fun DayStatusContainer(
    isMarked: Boolean,
    content: @Composable () -> Unit
) {
    if (isMarked) {
        Surface(
            modifier = Modifier.size(36.dp),
            shape = RoundedCornerShape(8.dp),
            color = CmpAppTheme.colors.controlsSecondaryActive
        ) {
            content()
        }
    } else {
        content()
    }
}

private fun getShape(
    isMarked: Boolean,
    inRange: Boolean,
    day: Long,
    from: Long,
    to: Long,
    indexInWeek: Int
): Shape {
    val startRoundedCornerShape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
    val endRoundedCornerShape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
    return when {
        isMarked -> when {
            (day == from && to < 0) -> RoundedCornerShape(8.dp)
            (day == from) -> startRoundedCornerShape
            (day == to) -> endRoundedCornerShape
            else -> RectangleShape
        }
        inRange -> when {
            (indexInWeek == 0) -> startRoundedCornerShape
            (indexInWeek == 6) -> endRoundedCornerShape
            else -> RectangleShape
        }
        else -> RectangleShape
    }
}