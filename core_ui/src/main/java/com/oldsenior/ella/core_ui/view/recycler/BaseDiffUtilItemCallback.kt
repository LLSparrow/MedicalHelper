package com.oldsenior.ella.core_ui.view.recycler

import androidx.recyclerview.widget.DiffUtil

/**
 * Util class for comparing items
 * @param T type of item
 */
abstract class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<ItemContainer>() {
}