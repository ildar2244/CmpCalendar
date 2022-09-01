package ru.axdar.ui_kit.components.calendar

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.axdar.ui_kit.R
import ru.axdar.ui_kit.components.calendar.model.CalendarMonth
import ru.axdar.ui_kit.components.calendar.model.CalendarState
import ru.axdar.ui_kit.components.calendar.roll.Roller
import ru.axdar.ui_kit.components.calendar.units.*
import ru.axdar.ui_kit.core.theme.CmpAppTheme

private const val FILTER_DATE_FORMAT = "yyyy-MM-dd"
private const val FULL_DAY = 1000 * 60 * 60 * 24
private const val ONE_MILLISECOND = 1

@Composable
fun CalendarDialogBox(
    state: CalendarState,
    from: Long,
    to: Long,
    onConfirmClick: (from: String, to: String?) -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    content()

    when (state) {
        CalendarState.Show -> CalendarDialog(
            from = from,
            to = to,
            onConfirmClick = onConfirmClick,
            onCancel = onCancel,
            onDismiss = onDismiss
        )
        CalendarState.Hide -> {}
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CalendarDialog(
    from: Long,
    to: Long,
    onConfirmClick: (from: String, to: String?) -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit,
) {
    var fromState by rememberSaveable {
        mutableStateOf(from)
    }
    var toState by rememberSaveable {
        mutableStateOf(to)
    }
    DatePicker(
        from = fromState,
        to = toState,
        onDaySelected = { newDate ->
            onDayClick(
                newDate = newDate,
                from = fromState,
                onFromChanged = { from -> fromState = from },
                to = toState,
                onToChanged = { to -> toState = to }
            )
        },
        onConfirmClick = {
            if (fromState > 0) {
                val formattedFrom = fromState.toFormattedString(FILTER_DATE_FORMAT)
                val formattedTo = if (toState < 0) {
                    (fromState + FULL_DAY - ONE_MILLISECOND).toFormattedString(FILTER_DATE_FORMAT)
                } else {
                    (toState + FULL_DAY - ONE_MILLISECOND).toFormattedString(FILTER_DATE_FORMAT)
                }
                onConfirmClick(formattedFrom, formattedTo)
            }
        },
        onCancel = onCancel,
        onDismiss = onDismiss
    )
}

private fun onDayClick(
    newDate: Long,
    from: Long,
    onFromChanged: (Long) -> Unit,
    to: Long,
    onToChanged: (Long) -> Unit,
) {
    when {
        (from < 0 && to < 0) -> onFromChanged(newDate)
        (from > 0 && to > 0) -> when {
            (newDate > from && newDate < to) -> onFromChanged(newDate)
            (newDate < from) -> onFromChanged(newDate)
            (newDate > to) -> onToChanged(newDate)
            (to == newDate) -> onToChanged(-1L)
            (from == newDate) -> {
                onFromChanged(to)
                onToChanged(-1L)
            }
        }
        (from > 0 && to < 0) -> when {
            newDate > from -> onToChanged(newDate)
            newDate == from -> onFromChanged(-1L)
            newDate < from -> {
                onToChanged(from)
                onFromChanged(newDate)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun DatePicker(
    from: Long,
    to: Long,
    onDaySelected: (daySelected: Long) -> Unit,
    onConfirmClick: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    var currentMonth by remember {
        mutableStateOf(from.toCalendarMonth())
    }
    val months = stringArrayResource(id = R.array.months)
    val years = getYears(currentYear = currentMonth.year)
    var isRollOpen by remember {
        mutableStateOf(false)
    }
    var rollHeaderYear by remember {
        mutableStateOf(currentMonth.year)
    }
    var rollHeaderMonth by remember {
        mutableStateOf(currentMonth.monthNumber)
    }

    fun setupRollHeader(currentMonth: CalendarMonth) {
        rollHeaderYear = currentMonth.year
        rollHeaderMonth = currentMonth.monthNumber
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                .width(width = 360.dp)
                .wrapContentHeight(unbounded = true)
                .background(
                    color = CmpAppTheme.colors.backgroundPrimary,
                    shape = RoundedCornerShape(16.dp)
                )
                .animateContentSize()
        ) {
            Crossfade(targetState = isRollOpen) { state ->
                Column {
                    if (!state) {
                        Header {
                            YearSelectionPanel(
                                month = months[currentMonth.monthNumber],
                                year = currentMonth.year.toString(),
                                onClick = { isRollOpen = !isRollOpen }
                            )
                            MonthSwitchButtons(
                                onSlideMonthToBack = {
                                    currentMonth =
                                        getPreviousMonth(currentMonth.year, currentMonth.monthNumber)
                                    setupRollHeader(currentMonth)
                                },
                                onSlideMonthToForward = {
                                    currentMonth =
                                        getNextMonth(currentMonth.year, currentMonth.monthNumber)
                                    setupRollHeader(currentMonth)
                                }
                            )
                        }
                        CalendarBody(
                            month = currentMonth,
                            from = from,
                            to = to,
                            onDayClicked = { day -> onDaySelected(day) }
                        )
                        BottomPanel {
                            BottomPanelWithSelectedDates(
                                startDate = from.toFormattedString(),
                                endDate = to.toFormattedString()
                            )
                            BottomButtons(
                                onCancelClick = onCancel,
                                onConfirmClick = onConfirmClick
                            )
                        }
                    } else {
                        Header {
                            YearSelectionPanel(
                                month = months[rollHeaderMonth],
                                year = rollHeaderYear.toString(),
                                isRollOpen = isRollOpen,
                                onClick = { isRollOpen = !isRollOpen }
                            )
                        }
                        Roller(
                            years = years,
                            currentYear = years.indexOf(currentMonth.year),
                            months = months,
                            currentMonth = currentMonth.monthNumber,
                            onSelectItems = { y, m ->
                                rollHeaderYear = y
                                rollHeaderMonth = m
                            }
                        )
                        BottomPanel {
                            BottomButtons(
                                onCancelClick = {
                                    rollHeaderYear = currentMonth.year
                                    rollHeaderMonth = currentMonth.monthNumber
                                    isRollOpen = !isRollOpen
                                },
                                onConfirmClick = {
                                    isRollOpen = !isRollOpen
                                    currentMonth = getMonth(
                                        year = rollHeaderYear,
                                        month = rollHeaderMonth
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}