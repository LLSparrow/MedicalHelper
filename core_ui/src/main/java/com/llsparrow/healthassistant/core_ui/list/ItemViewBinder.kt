package com.llsparrow.healthassistant.core_ui.list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewBinder<T, in VH : RecyclerView.ViewHolder>(
    val modelClass: Class<out T>
) : DiffUtil.ItemCallback<T>() {

    abstract fun createViewHolder(binding: ViewDataBinding): BaseViewHolder<T>

    abstract fun bindViewHolder(model: T, viewHolder: VH)

    abstract fun getItemType(): Int

    open fun onViewRecycled(viewHolder: VH) = Unit

    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit
}