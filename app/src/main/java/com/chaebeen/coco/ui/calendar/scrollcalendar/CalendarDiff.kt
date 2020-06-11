package com.chaebeen.coco.ui.calendar.scrollcalendar

import androidx.recyclerview.widget.DiffUtil
import com.chaebeen.coco.ui.calendar.scrollcalendar.Day

class CalendarDiff (private val old: List<Day>, private val new: List<Day>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].calendar.time == new[newItemPosition].calendar.time

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]

    fun calculateDiff() : DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(this, false)
    }

}