package com.oldsenior.ella.core_ui.view.recycler

abstract class ItemContainer(val itemType: ItemType) {
    enum class ItemType {
        HEADER, FOOTER, ITEM
    }

    fun value() = this

    fun isHeader() = itemType == ItemType.HEADER

    fun isNotHeader() = itemType != ItemType.HEADER

    fun isFooter() = itemType == ItemType.FOOTER

    fun isNotFooter() = itemType != ItemType.FOOTER

    fun isItem() = itemType == ItemType.ITEM

    fun isNotItem() = itemType != ItemType.ITEM

    fun isTheSameType(type: ItemType) = itemType == type

}