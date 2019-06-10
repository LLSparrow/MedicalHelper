package com.oldsenior.ella.medicalhelper.listsample

import com.oldsenior.ella.core_ui.view.recycler.BaseDiffUtilItemCallback
import com.oldsenior.ella.core_ui.view.recycler.ItemContainer

data class EventItem(
    val body: String
) : ItemContainer(ItemType.ITEM) {


}