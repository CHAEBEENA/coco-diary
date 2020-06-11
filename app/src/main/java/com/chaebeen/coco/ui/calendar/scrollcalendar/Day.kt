package com.chaebeen.coco.ui.calendar.scrollcalendar

import java.util.*

data class Day(
    var calendar: Calendar,
    var state: DayState,
    var isToday: Boolean,
    var isSelected: Boolean
) {
}

enum class DayState {
    PreviousMonth,
    ThisMonth,
    NextMonth
}