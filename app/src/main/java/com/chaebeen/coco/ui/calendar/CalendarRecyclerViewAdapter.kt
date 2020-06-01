package com.chaebeen.coco.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaebeen.coco.databinding.ItemCalendarBinding
import java.util.*

class CalendarRecyclerViewAdapter(val fragment: CalendarFragment) : RecyclerView.Adapter<CalendarViewHolder<*>>() {

    val baseCalendar = BaseCalendar()

    init {
        baseCalendar.initBaseCalendar {
            refreshView(it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(baseCalendar.data[position] != 0) 0
        else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder<*> = when(viewType) {
        0 -> DateViewHolder(ItemCalendarBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        1 -> EmptyViewHolder(ItemCalendarBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        else -> throw IllegalStateException("Unsupported view type")
    }


    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: CalendarViewHolder<*>, position: Int) = when(holder) {
        is DateViewHolder -> { holder.bind(baseCalendar.data[position]) }
        is EmptyViewHolder -> { }
    }

    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth {
            refreshView(it)
        }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth {
            refreshView(it)
        }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        fragment.refreshCurrentMonth(calendar)
    }
}