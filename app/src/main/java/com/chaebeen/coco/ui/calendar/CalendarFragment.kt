package com.chaebeen.coco.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.chaebeen.coco.databinding.FragmentCalendarBinding
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment(), OnCalendarClickedListener {

    companion object {
        fun newInstance() = CalendarFragment()

    }

    private lateinit var viewModel: CalendarViewModel

    private lateinit var scheduleRecyclerViewAdapter: CalendarRecyclerViewAdapter

    lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CalendarViewModel::class.java)
        // TODO: Use the ViewModel

        initView()
    }

    fun initView() {

        scheduleRecyclerViewAdapter = CalendarRecyclerViewAdapter(this)

        rv_schedule.layoutManager = GridLayoutManager(requireContext(), BaseCalendar.DAYS_OF_WEEK)
        rv_schedule.adapter = scheduleRecyclerViewAdapter
        // rv_schedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        // rv_schedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        tv_prev_month.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToPrevMonth()
        }

        tv_next_month.setOnClickListener {
            scheduleRecyclerViewAdapter.changeToNextMonth()
        }
    }

    fun refreshCurrentMonth(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy MM", Locale.KOREAN)
        tv_current_month.text = sdf.format(calendar.time)
    }

    override fun onCalendarClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

interface OnCalendarClickedListener {
    fun onCalendarClicked()
}
