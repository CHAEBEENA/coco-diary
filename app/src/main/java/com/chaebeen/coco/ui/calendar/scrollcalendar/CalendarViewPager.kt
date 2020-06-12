package com.chaebeen.coco.ui.calendar.scrollcalendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.time.Year
import java.util.*

open class CalendarViewPager(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    var onDayClickListener: ((Day) -> Unit)? = null
    set(value){
        field = value
        (adapter as? CalendarViewPagerAdapter)?.onDayClickListener = field
    }

    var onDayLongClickedListener: ((Day) -> Boolean)? = null
    set(value) {
        field = value
        (adapter as? CalendarViewPagerAdapter)?.onDayLongClickListener = field
    }

    var onCalendarChangeListener: ((Calendar) -> Unit)? = null

    var onYearMonthChangeListener: ((Calendar) -> Unit)? = null
    set(value){
        field = value
        (adapter as? CalendarViewPagerAdapter)?.onYearMonthChangeListener = field
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        if(adapter is CalendarViewPagerAdapter) {
            this.clearOnPageChangeListeners()

            adapter.onDayClickListener = this.onDayClickListener
            adapter.onDayLongClickListener = this.onDayLongClickedListener

            setCurrentItem(CalendarViewPagerAdapter.MAX_VALUE / 2, false)
            this.addOnPageChangeListener(pageChangeListener)
            this.addOnPageChangeListener(pageChangeListener)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val mode = MeasureSpec.getMode(heightMeasureSpec)
        if(mode == MeasureSpec.AT_MOST) {
            val view = focusedChild ?: getChildAt(0)
            view.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val newHeight = view.measuredHeight

            val exactlyHeightMeasureSpec = MeasureSpec.makeMeasureSpec(newHeight, MeasureSpec.EXACTLY)
            super.onMeasure(widthMeasureSpec, exactlyHeightMeasureSpec)
        }
    }

    private val pageChangeListener = object : OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) { }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) { }

        override fun onPageSelected(position: Int) {
            val calendar = (adapter as? CalendarViewPagerAdapter)?.getCalendar(position) ?: return
            onCalendarChangeListener?.invoke(calendar)
        }

    }

    fun onYearMonthChanged(year: Int, month: Int) {
        val calendar = (adapter as? CalendarViewPagerAdapter)?.getCalendar(year, month) ?: return
        onCalendarChangeListener?.invoke(calendar)
        val current = Calendar.getInstance()
        val diff = (year - current.get(Calendar.YEAR)) * 12 + (month - current.get(Calendar.MONTH))
       // val thisTime = calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH)
       // val compareTime = cal.get(Calendar.YEAR) * 12 + cal.get(Calendar.MONTH)
        setCurrentItem(CalendarViewPagerAdapter.MAX_VALUE / 2 + diff, false)
        Log.d("coco-dev-calendar", "viewpager")

    }

}

