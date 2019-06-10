package com.oldsenior.ella.medicalhelper.listsample

import android.view.View
import com.oldsenior.ella.core_ui.view.recycler.*
import com.oldsenior.ella.medicalhelper.R

class SampleSection :
    Section<EventHeader, EventItem, EventFooter>() {

    override fun headerFilter(search: String, item: ItemContainer): Boolean {
        return (item.value() as EventHeader).title.contains(search, true)
    }

    override fun itemFilter(search: String, item: ItemContainer): Boolean {
        return (item.value() as EventItem).body.contains(search, true)
    }

    override fun getItemViewHolder(view: View): BaseViewHolder<EventItem> {
        return EventItemViewHolder(view, itemClickListener)
    }

    override fun getHeaderViewHolder(view: View): BaseViewHolder<EventHeader> {
        return EventHeaderViewHolder(view, headerClickListener)
    }

    override fun getFooterViewHolder(view: View): BaseViewHolder<EventFooter> {
        return EventFooterViewHolder(view)
    }

    override fun getSectionParams(): SectionParams {
        return SectionParams.builder()
            .itemResourceId(R.layout.event_section_item)
            .headerResourceId(R.layout.event_section_header)
            .footerResourceId(R.layout.event_section_footer)
            .shouldFilterHeader(true)
            .build()
    }

    override fun getDiffUtilItemCallback(): BaseDiffUtilItemCallback {
        return object : BaseDiffUtilItemCallback() {
            override fun areItemsTheSame(oldItem: ItemContainer, newItem: ItemContainer): Boolean {
                return (oldItem.value() as EventItem) == (newItem.value() as EventItem)
            }

            override fun areContentsTheSame(oldItem: ItemContainer, newItem: ItemContainer) =
                (oldItem.value() as EventItem).body == (newItem.value() as EventItem).body
        }
    }
}