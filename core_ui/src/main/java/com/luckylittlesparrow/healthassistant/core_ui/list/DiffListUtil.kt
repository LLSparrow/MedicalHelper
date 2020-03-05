package com.luckylittlesparrow.healthassistant.core_ui.list

import androidx.recyclerview.widget.DiffUtil


class DiffListUtil<T>(private val callback: DiffUtil.ItemCallback<T>) : DiffUtil.Callback() {

    private lateinit var oldList: List<T>

    private lateinit var newList: List<T>

    fun submitLists(oldList: List<T>, newList: List<T>) {
        this.newList = ArrayList(newList)
        this.oldList = ArrayList(oldList)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList.size < oldItemPosition || newList.size < newItemPosition) return false

        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]

        return callback.areItemsTheSame(
            oldElement,
            newElement
        )
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList.size < oldItemPosition || newList.size < newItemPosition) return false

        val oldElement = oldList[oldItemPosition]
        val newElement = newList[newItemPosition]

        return callback.areContentsTheSame(
            oldElement,
            newElement
        )
    }
}