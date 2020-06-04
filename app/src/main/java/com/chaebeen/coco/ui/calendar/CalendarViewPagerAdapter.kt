package com.chaebeen.coco.ui.calendar

import android.app.ActionBar
import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import org.w3c.dom.Text
import java.time.DayOfWeek
import java.util.*

open class CalendarViewPagerAdapter(
    val context: Context,
    base: Calendar = Calendar.getInstance(),
    val startingAt: DayOfWeek = DayOfWeek.Sunday
) : PagerAdapter() {

    companion object {
        const val MAX_VALUE = 500
    }

    private val baseCalendar: Calendar = org.apache.commons.lang3.time.DateUtils.truncate(base, Calendar.DAY_OF_MONTH).apply {
        set(Calendar.DAY_OF_MONTH, 1)
        firstDayOfWeek = Calendar.SUNDAY + startingAt.getDifference()
        minimalDaysInFirstWeek = 1
    }

    private var viewContainer: ViewGroup? = null

    var selectedDay: Date? = null
        set(value) {
            field = value
            notifyCalendarItemChanged()
        }

    var onDayClickListener: ((Day) -> Unit)? = null

    var onDayLongClickListener: ((Day) -> Boolean)? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun getCount(): Int = MAX_VALUE

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val recyclerView = RecyclerView(context).apply {
            layoutManager = GridLayoutManager(context, 7)
            isNestedScrollingEnabled = false
            hasFixedSize()

            adapter = object : DayGridAdapter(context, getCalendar(position), startingAt, selectedDay) {
                override fun onBindViewHolder(holder: RecyclerView.ViewHolder, day: Day) {
                    holder.itemView.setOnClickListener {
                        this@CalendarViewPagerAdapter.selectedDay = day.calendar.time
                        this@CalendarViewPagerAdapter.onDayClickListener?.invoke(day)
                        notifyCalendarItemChanged()
                    }
                    holder.itemView.setOnLongClickListener {
                        if(this@CalendarViewPagerAdapter.onDayLongClickListener != null) {
                            this@CalendarViewPagerAdapter.onDayLongClickListener!!.invoke(day)
                        } else {
                            false
                        }
                    }
                    this@CalendarViewPagerAdapter.onBindView(holder.itemView, day)
                }

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                    object: RecyclerView.ViewHolder(this@CalendarViewPagerAdapter.onCreateView(parent, viewType)) {}

            }
        }
        container.addView(recyclerView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        viewContainer = container

        return recyclerView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    fun getCalendar(position: Int): Calendar {
        return (baseCalendar.clone() as Calendar).apply {
            add(Calendar.MONTH, position - MAX_VALUE / 2)
        }
    }

    private fun notifyCalendarItemChanged() {
        val views = viewContainer ?: return
        (0 until views.childCount).forEach {
            ((views.getChildAt(it) as? RecyclerView)?.adapter as? DayGridAdapter)?.updateItems(selectedDay)
        }
    }

    open fun onCreateView(parent: ViewGroup, viewType: Int): View {
        return TextView(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 96)
        }
    }

    open fun onBindView(view: View, day: Day) {
        val textView = view as TextView
        textView.text = when(day.state) {
            DayState.ThisMonth -> day.calendar.get(Calendar.DAY_OF_MONTH).toString()
            else -> ""
        }
    }



    enum class DayOfWeek {
        Sunday,
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday;

        //기준(일요일)에서 차이
        fun getDifference(): Int {
            return when (this) {
                Sunday -> 0
                Monday -> 1
                Tuesday -> 2
                Wednesday -> 3
                Thursday -> 4
                Friday -> 5
                Saturday -> 6
            }
        }

        fun isLessFirstWeek(calendar: Calendar): Boolean {
            return calendar.get(Calendar.DAY_OF_WEEK) < getDifference() + 1
        }

        fun isMoreLastWeek(calendar: Calendar): Boolean {
            val end = org.apache.commons.lang3.time.DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH)
            end.add(Calendar.MONTH, 1)
            end.add(Calendar.DATE, -1)
            return end.get(Calendar.DAY_OF_WEEK) < getDifference() + 1
        }

    }



}