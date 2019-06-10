package com.oldsenior.ella.medicalhelper.listsample

import android.view.View
import android.widget.TextView
import com.oldsenior.ella.core_ui.view.recycler.BaseViewHolder
import com.oldsenior.ella.medicalhelper.R

class EventHeaderViewHolder constructor(view: View, itemClickedListener: (EventHeader) -> Unit) :
    BaseViewHolder<EventHeader>(view, itemClickedListener) {

    private val eventItemDayTextView: TextView = view.findViewById(R.id.eventItemDayTextView)

    override fun bindItem(item: EventHeader) {
        super.bindItem(item)
        eventItemDayTextView.text = item.title
    }
}