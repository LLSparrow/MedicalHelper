package com.llsparrow.healthassistant.feature_medicine_list.presentation.list

import androidx.databinding.ViewDataBinding
import com.llsparrow.healthassistant.core_ui.list.ItemViewBinder
import com.llsparrow.healthassistant.feature_medicine_list.R
import com.llsparrow.healthassistant.feature_medicine_list.presentation.model.MedicineItem

//internal class MedicineViewBinder(val block: (data: Medicine) -> Unit) :
internal class MedicineViewBinder : ItemViewBinder<MedicineItem, MedicineListViewHolder>(
    MedicineItem::class.java) {

    override fun createViewHolder(binding: ViewDataBinding): MedicineListViewHolder {
        return MedicineListViewHolder(
            binding
        )
    }

    override fun bindViewHolder(model: MedicineItem, viewHolder: MedicineListViewHolder) {
        viewHolder.bind(model)
    }

    override fun getItemType() = R.layout.medicine_item_layout

    override fun areContentsTheSame(oldItem: MedicineItem, newItem: MedicineItem): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.desc == newItem.desc &&
                oldItem.logoUri == newItem.logoUri
    }

    override fun areItemsTheSame(oldItem: MedicineItem, newItem: MedicineItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }
}