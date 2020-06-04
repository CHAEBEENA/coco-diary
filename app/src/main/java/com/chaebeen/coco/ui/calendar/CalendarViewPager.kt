package com.chaebeen.coco.ui.calendar

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.*
import java.util.jar.Attributes

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

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        if(adapter is CalendarViewPagerAdapter) {
            this.clearOnPageChangeListeners()

            adapter.onDayClickListener = this.onDayClickListener
            adapter.onDayLongClickListener = this.onDayLongClickedListener

            setCurrentItem(CalendarViewPagerAdapter.MAX_VALUE / 2, false)
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























}