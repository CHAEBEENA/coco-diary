package com.chaebeen.coco.ui.calendar

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.lang3.time.DateUtils
import java.util.*
import kotlin.properties.Delegates

abstract class DayGridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private val context: Context
    private val calendar: Calendar
    private val weekOfMonth: Int
    private val startDate: Calendar

    var items: List<Day> by Delegates.observable(emptyList()) {_,old,new ->
        CalendarDiff(old, new).calculateDiff().dispatchUpdatesTo(this)
    }

    constructor(context: Context, calendar: Calendar, startingAt: CalendarViewPagerAdapter.DayOfWeek, preselectDay: Date? = null) : super() {
        this.context = context
        this.calendar = calendar

        val start = org.apache.commons.lang3.time.DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH)
        if(start.get(Calendar.DAY_OF_WEEK) != (startingAt.getDifference() + 1)) {
            start.set(Calendar.DAY_OF_MONTH, if(startingAt.isLessFirstWeek(calendar)) - startingAt.getDifference() else 0)
            start.add(Calendar.DAY_OF_MONTH, -start.get(Calendar.DAY_OF_WEEK) + 1 + startingAt.getDifference())
        }
        startDate = start
        this.weekOfMonth = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)+ (if(startingAt.isLessFirstWeek(calendar)) 1 else 0)- (if(startingAt.isMoreLastWeek(calendar))1 else 0)

        updateItems(preselectDay)
    }

    override fun getItemCount(): Int = 7 * weekOfMonth


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder, items[holder.layoutPosition])
    }

    abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, day: Day)

    fun updateItems(selectedDate: Date? = null) {
        val now = Calendar.getInstance()

        this.items = (0..itemCount).map {
            val cal = Calendar.getInstance().apply { time = startDate.time }
            cal.add(Calendar.DAY_OF_MONTH, it)

            val thisTime = calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH)
            val compareTime = cal.get(Calendar.YEAR) * 12 + cal.get(Calendar.MONTH)

            val state = when(thisTime.compareTo(compareTime)) {
                -1 -> DayState.NextMonth
                0 -> DayState.ThisMonth
                1 -> DayState.PreviousMonth
                else -> throw IllegalStateException()
            }
            val isSelected = when(selectedDate) {
                null -> false
                else -> DateUtils.isSameDay(cal.time, selectedDate)
            }
            val isToday = DateUtils.isSameDay(cal, now)

            Day(cal, state, isToday, isSelected)
        }
    }

























}