package ru.axdar.ui_kit.components.calendar.model

import java.util.*

data class CalendarMonth(
    val year: Int,
    val monthNumber: Int,
    val count: Int,
    val firstDay: DayOfWeek,
    val currentDay: Int = -1,
) {
    private val days = mutableListOf<Long>().apply {
        for (i in 1..firstDay.ordinal) {
            add(-1L)
        }

        val calendar = Calendar.getInstance(Locale.getDefault()).apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, monthNumber)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        for (i in 1..count) {
            calendar.set(Calendar.DATE, i)
            add(calendar.timeInMillis)
        }
    }.toList()

    val weeks = lazy { days.chunked(7).map { completeWeek(it) } }

    private fun completeWeek(list: List<Long>): List<Long> {
        var gapsToFill = 7 - list.size

        return if (gapsToFill != 0) {
            val mutableList = list.toMutableList()
            while (gapsToFill > 0) {
                mutableList.add(-1L)
                gapsToFill--
            }
            mutableList
        } else {
            list
        }
    }
}
