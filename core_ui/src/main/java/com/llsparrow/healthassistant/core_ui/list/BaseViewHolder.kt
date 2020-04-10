package com.llsparrow.healthassistant.core_ui.list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(protected val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T?)
}

