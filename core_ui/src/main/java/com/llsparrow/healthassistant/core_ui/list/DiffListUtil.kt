package com.llsparrow.healthassistant.core_ui.list

import androidx.recyclerview.widget.DiffUtil


class DiffListUtil(private val viewBinders: Map<ItemClass, ItemBinder>) : DiffUtil.Callback() {

    private lateinit var oldList: List<Any>

    private lateinit var newList: List<Any>

    fun submitLists(oldList: List<Any>, newList: List<Any>) {
        this.newList = ArrayList(newList)
        this.oldList = ArrayList(oldList)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList.size < oldItemPosition || newList.size < newItemPosition) return false

        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]

        if (oldElement::class != newElement::class) return false

        return viewBinders[oldElement::class.java]?.areItemsTheSame(oldElement, newElement) ?: false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList.size < oldItemPosition || newList.size < newItemPosition) return false

        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]

        if (oldElement::class != newElement::class) return false

        return viewBinders[oldElement::class.java]?.areContentsTheSame(oldElement, newElement) ?: false
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size
}