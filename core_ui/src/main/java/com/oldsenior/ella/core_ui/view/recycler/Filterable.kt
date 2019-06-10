package com.oldsenior.ella.core_ui.view.recycler

import androidx.recyclerview.widget.DiffUtil

interface Filterable {
    fun filter(search: CharSequence): Pair<List<ItemContainer>, List<ItemContainer>>?
}