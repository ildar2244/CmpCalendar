package ru.axdar.ui_kit.components.calendar

import ru.axdar.ui_kit.components.calendar.model.CalendarMonth
import ru.axdar.ui_kit.components.calendar.model.DayOfWeek
import java.text.SimpleDateFormat
import java.util.*

private const val MIDNIGHT_DATE_FORMAT = "yyyy-MM-dd"
private const val FIRST_NUMBER_OF_MONTH = 1
private const val YEAR_OFFSET = 20

fun isWeekendDay(index: Int): Boolean {
    return index == 5 || index == 6
}

fun dayIsMarked(day: Long,  from: Long, to: Long): Boolean {
    return when {
        (day < 0) -> false
        else -> (from > 0 || to > 0) && (day == from || day == to)
    }
}

fun dayInRange(day: Long,  from: Long, to: Long): Boolean {
    return when {
        (day < 0) -> false
        (from < 0 || to < 0) -> false
        (day > from && day < to) -> true
        else -> false
    }
}

fun String?.toDateLongMillis(): Long {
    if (this == null || this.isBlank()) {
        return -1L
    }
    return try {
        val parser = SimpleDateFormat(MIDNIGHT_DATE_FORMAT)
        val date = parser.parse(this)
        date.time
    } catch (ex: Exception) {
        -1L
    }
}

internal fun Long.toFormattedString(pattern: String = "dd.MM.yyyy"): String {
    if (this < 0)
        return ""
    val date = Date(this)
    val format = SimpleDateFormat(pattern)
    return format.format(date)
}

internal fun Long.isCurrentDay(current: Int): Boolean {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = this
    return calendar.get(Calendar.DATE) == current
}

internal fun getNextMonth(currentYear: Int, currentMonth: Int): CalendarMonth {
    if (currentMonth < 0 || currentMonth > 11) {
        error("Month number must be between 0 and 11.")
    }
    return if (currentMonth == 11)
        getMonth(currentYear + 1, 0)
    else
        getMonth(currentYear, currentMonth + 1)
}

internal fun getPreviousMonth(currentYear: Int, currentMonth: Int): CalendarMonth {
    if (currentMonth < 0 || currentMonth > 11) {
        error("Month number must be between 0 and 11.")
    }
    return if (currentMonth == 0)
        getMonth(currentYear - 1, 11)
    else
        getMonth(currentYear, currentMonth - 1)
}

internal fun getYears(currentYear: Int): Array<Int> {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = 0
    val epochYear = calendar.get(Calendar.YEAR)
    return Array((currentYear - YEAR_OFFSET) - epochYear) { i ->
        epochYear + i
    }
}

internal fun Long.toCalendarMonth(): CalendarMonth {
    return if (this <= 0)
        getCurrentMonth()
    else
        getMonth(this)
}

private fun getCurrentMonth(): CalendarMonth {
    val calendar = Calendar.getInstance(Locale.getDefault())
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val count: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDay = getFirstDayOfWeekInMonth(calendar)

    return CalendarMonth(
        year = year,
        count = count,
        monthNumber = month,
        firstDay = DayOfWeek.values()[firstDay],
        currentDay = day
    )
}

private fun getMonth(millis: Long): CalendarMonth {
    val calendar = Calendar.getInstance(Locale.getDefault())
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDate = calendar.get(Calendar.DATE)
    calendar.timeInMillis = millis
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    calendar.set(Calendar.DATE, FIRST_NUMBER_OF_MONTH)
    val count: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDay = getFirstDayOfWeekInMonth(calendar)
    val current = if (currentYear == year && currentMonth == month) currentDate else -1

    return CalendarMonth(
        year = year,
        count = count,
        monthNumber = month,
        firstDay = DayOfWeek.values()[firstDay],
        currentDay = current
    )
}

internal fun getMonth(year: Int, month: Int): CalendarMonth {
    if (month < 0 || month > 11) {
        error("Month number must be between 0 and 11.")
    }
    val calendar = Calendar.getInstance(Locale.getDefault())
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDate = calendar.get(Calendar.DATE)
    calendar.set(year, month, FIRST_NUMBER_OF_MONTH)
    val yearC = calendar.get(Calendar.YEAR)
    val monthC = calendar.get(Calendar.MONTH)
    val count: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDay = getFirstDayOfWeekInMonth(calendar)
    val current = if (currentYear == yearC && currentMonth == monthC) currentDate else -1

    return CalendarMonth(
        year = yearC,
        count = count,
        monthNumber = monthC,
        firstDay = DayOfWeek.values()[firstDay],
        currentDay = current
    )
}

private fun getFirstDayOfWeekInMonth(calendar: Calendar): Int {
    calendar.set(Calendar.DATE, FIRST_NUMBER_OF_MONTH)
    val firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 2
    return if (firstDay < 0) 6 else firstDay
}
