package com.chaebeen.coco.ui.calendar

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chaebeen.coco.databinding.ItemCalendarBinding


sealed class CalendarViewHolder<T: Int>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}


class DateViewHolder(
    val binding: ItemCalendarBinding
): CalendarViewHolder<Int>(binding.root) {
    override fun bind(item: Int) {
        binding.date.text = item.toString()
    }
}

class EmptyViewHolder(
    val binding: ItemCalendarBinding
): CalendarViewHolder<Int>(binding.root) {
    override fun bind(item: Int) {

    }
}

