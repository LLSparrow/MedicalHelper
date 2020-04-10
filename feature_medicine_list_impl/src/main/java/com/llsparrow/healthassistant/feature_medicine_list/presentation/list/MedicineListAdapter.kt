package com.llsparrow.healthassistant.feature_medicine_list.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_ui.list.BaseListAdapter
import com.llsparrow.healthassistant.core_ui.list.ItemBinder
import com.llsparrow.healthassistant.feature_medicine_list.databinding.MedicineItemLayoutBinding
import com.llsparrow.healthassistant.feature_medicine_list.presentation.model.MedicineItem
import com.llsparrow.healthassistant.feature_medicine_list.presentation.MedicineListViewModel
import javax.inject.Inject

@FeatureScope
internal class MedicineListAdapter @Inject constructor() : BaseListAdapter<MedicineListViewModel>() {

    init {
        val viewBinder =
            MedicineViewBinder()

        @Suppress("UNCHECKED_CAST")
        viewBinders[viewBinder.modelClass] = viewBinder as ItemBinder
    }

    override fun provideBindingComponent(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return MedicineItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply {
                this.viewmodel = this@MedicineListAdapter.viewModel
            }
    }

    override fun filterConstraint(item: Any, str: String): Boolean {
        if (item is MedicineItem) return item.name.contains(str, true)
        else return false
    }
}