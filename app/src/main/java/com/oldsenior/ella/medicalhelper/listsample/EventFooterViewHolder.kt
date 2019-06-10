package com.oldsenior.ella.medicalhelper.listsample

import android.view.View
import android.widget.TextView
import com.oldsenior.ella.core_ui.view.recycler.BaseViewHolder
import com.oldsenior.ella.medicalhelper.R

class EventFooterViewHolder constructor(view: View) :
    BaseViewHolder<EventFooter>(view) {

    private val eventItemDayTextView: TextView = view.findViewById(R.id.eventFooterItemDayTextView)

    override fun bindItem(item: EventFooter) {
        super.bindItem(item)
        eventItemDayTextView.text = item.title
    }
}