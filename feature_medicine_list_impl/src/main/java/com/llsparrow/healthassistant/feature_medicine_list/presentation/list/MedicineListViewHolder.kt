package com.llsparrow.healthassistant.feature_medicine_list.presentation.list

import androidx.databinding.ViewDataBinding
import com.llsparrow.healthassistant.core_ui.list.BaseViewHolder
import com.llsparrow.healthassistant.feature_medicine_list.BR
import com.llsparrow.healthassistant.feature_medicine_list.presentation.model.MedicineItem

internal class MedicineListViewHolder(binding: ViewDataBinding) : BaseViewHolder<MedicineItem>(binding) {
    override fun bind(item: MedicineItem?) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}