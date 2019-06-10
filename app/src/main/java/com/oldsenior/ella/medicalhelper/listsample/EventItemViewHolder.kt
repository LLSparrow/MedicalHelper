package com.oldsenior.ella.medicalhelper.listsample

import android.view.View
import android.widget.TextView
import com.oldsenior.ella.core_ui.view.recycler.BaseViewHolder
import com.oldsenior.ella.medicalhelper.R


class EventItemViewHolder constructor(view: View, itemClickedListener: (EventItem) -> Unit) :
    BaseViewHolder<EventItem>(view, itemClickedListener) {

    private val eventItemDescriptionTextView: TextView = view.findViewById(R.id.eventItemDescriptionTextView)


    override fun bindItem(item: EventItem) {
        super.bindItem(item)
        eventItemDescriptionTextView.text = item.body
    }
}