package com.chaebeen.coco.ui.calendar

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.text.format.DateUtils.formatDateTime
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chaebeen.coco.R
import kotlinx.android.synthetic.main.fragment_scroll_calendar.*
import kotlinx.android.synthetic.main.item_scroll_calendar.view.*
import java.util.*

class ScrollCalendarFragment : Fragment() {

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

        calendar_view_pager.apply {
            adapter = CustomCalendarAdapter(requireContext())
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
        }


    }

    private fun setDateHeader(calendar: Calendar) {
        text_month.text = formatDateTime(requireContext(), calendar.timeInMillis,
            DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NO_MONTH_DAY)
    }

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