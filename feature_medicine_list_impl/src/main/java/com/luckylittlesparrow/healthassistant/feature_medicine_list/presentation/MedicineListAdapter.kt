package com.luckylittlesparrow.healthassistant.feature_medicine_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.luckylittlesparrow.healthassistant.feature_medicine_list.databinding.MedicineItemLayoutBinding
import com.luckylittlesparrow.healthassistant.core_ui.list.BaseListAdapter
import com.luckylittlesparrow.healthassistant.core_ui.list.ListViewHolder
import com.luckylittlesparrow.healthassistant.feature_medicine_list.BR
import com.luckylittlesparrow.healthassistant.feature_medicine_list.R
import com.luckylittlesparrow.healthassistant.feature_medicine_list.model.presentation.Medicine

internal class MedicineListAdapter : BaseListAdapter<Medicine, MedicineListViewModel>() {

    override val diffCallback = MedicineItemDiff

    override fun provideBindingComponent(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return MedicineItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply {
                this.viewmodel = this@MedicineListAdapter.viewModel
            }
    }

    override fun provideViewHolder(binding: ViewDataBinding): ListViewHolder<Medicine> {
        return MedicineListViewHolder(binding)
    }

    override fun filterConstraint(item: Medicine, str: String): Boolean = item.name.contains(str, true)

    override fun getItemViewType(position: Int): Int {
        return R.layout.medicine_item_layout
    }
}

object MedicineItemDiff : DiffUtil.ItemCallback<Medicine>() {
    override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.desc == newItem.desc &&
                oldItem.logoUri == newItem.logoUri
    }

    override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem.itemId == newItem.itemId
    }
}


class MedicineListViewHolder(binding: ViewDataBinding) : ListViewHolder<Medicine>(binding) {
    override fun bind(item: Medicine?) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}