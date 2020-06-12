package com.chaebeen.coco.ui.calendar.scrollcalendar

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.text.format.DateUtils.formatDateTime
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chaebeen.coco.R
import kotlinx.android.synthetic.main.fragment_scroll_calendar.*
import kotlinx.android.synthetic.main.item_scroll_calendar.view.*
import java.util.*

class ScrollCalendarFragment : Fragment(), OnYearMonthChangeListener {

    private lateinit var calendarAdapter : CustomCalendarAdapter

    private var year : Int = 0

    private var month: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scroll_calendar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setDateHeader(Calendar.getInstance())

        calendarAdapter = CustomCalendarAdapter(requireContext())

        calendar_view_pager.apply {
            adapter = calendarAdapter
            onDayClickListener = {
                Toast.makeText(requireContext(), it.calendar.time.toString(), Toast.LENGTH_SHORT).show()
            }
            onDayLongClickedListener = {
                Toast.makeText(requireContext(), "Long Clicked : " + it.calendar.time.toString(), Toast.LENGTH_SHORT).show()
                true
            }
            onCalendarChangeListener = {
                setDateHeader(it)
            }

            onYearMonthChangeListener = {
                setDateHeader(it)
            }
        }

        text_month.setOnClickListener {
           YearMonthPickerDialog(
               requireContext(), this, year, month
           ).show()
        }


    }

    private fun setDateHeader(calendar: Calendar) {
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        text_month.text = formatDateTime(requireContext(), calendar.timeInMillis,
            DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NO_MONTH_DAY)

    }

    override fun onYearMonthChanged(year: Int, month: Int) {
       //. CalendarViewPager(requireContext()).onYearMonthChanged(year, month)
        calendar_view_pager.onYearMonthChanged(year, month)
        Log.d("coco-dev-calendar","yearmonthlistener")
    }

}

interface OnYearMonthChangeListener {
    fun onYearMonthChanged(year: Int, month: Int)
}

class CustomCalendarAdapter(context: Context) : CalendarViewPagerAdapter(context) {
    override fun onCreateView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_scroll_calendar, parent, false)
    }

    override fun onBindView(view: View, day: Day) {
        if(day.state == DayState.ThisMonth) {
            view.visibility = View.VISIBLE
            view.text_background.visibility = if(day.isToday) View.VISIBLE else View.GONE
            view.text_day.text = day.calendar.get(Calendar.DAY_OF_MONTH).toString()
            view.view_dot.visibility = if(day.isSelected) View.VISIBLE else View.GONE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

}