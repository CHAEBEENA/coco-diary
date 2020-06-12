package com.chaebeen.coco.ui.calendar.scrollcalendar

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.chaebeen.coco.R
import kotlinx.android.synthetic.main.year_month_picker.view.*
import java.util.*

class YearMonthPickerDialog(
    private val context: Context,
    private val listener: OnYearMonthChangeListener,
    private val year: Int,
    private val month: Int
)  {

    fun show() {
        val alertDialog = AlertDialog.Builder(context).create()
        val view = LayoutInflater.from(context).inflate(R.layout.year_month_picker, null, false)
        alertDialog.setView(view)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val cal = Calendar.getInstance()

        view.picker_month.apply {
            minValue = 1
            maxValue = 12
            value = month + 1
        }

        view.picker_year.apply {
            minValue = 1980
            maxValue = 2099
            value = year
        }

        view.btn_cancel.setOnClickListener {
            alertDialog.cancel()
        }

        view.btn_confirm.setOnClickListener {
            listener.onYearMonthChanged(view.picker_year.value, view.picker_month.value -1)
            alertDialog.cancel()
        }

        alertDialog.show()
    }
}

