package com.oldsenior.ella.core_ui.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oldsenior.ella.core_ui.view.recycler.Section.State
import java.util.*
import kotlin.collections.ArrayList


class BaseListAdapter : RecyclerView.Adapter<BaseViewHolder<Nothing>>(), Filterable {
    private val diffUtilItemCallback = DiffListCallback()

    private inner class DiffListCallback : DiffUtil.Callback() {
        private lateinit var oldList: List<ItemContainer>
        private lateinit var newList: List<ItemContainer>

        fun submitLists(oldList: List<ItemContainer>, newList: List<ItemContainer>) {
            this.newList = newList
            this.oldList = oldList
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldElement = oldList[oldItemPosition]
            val newElement = newList[newItemPosition]

            if (!oldElement.value().isTheSameType(newElement.itemType)) return false

            val section = getSectionByItem(oldElement, newElement)
            return section?.areItemsTheSame(
                oldElement,
                newElement
            ) ?: false
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldElement = oldList[oldItemPosition]
            val newElement = newList[newItemPosition]

            if (!oldElement.value().isTheSameType(newElement.itemType)) return false

            val section = getSectionByItem(oldElement, newElement)
            return section?.areContentsTheSame(
                oldElement,
                newElement
            ) ?: false
        }

    }

    private fun getSectionByItem(item: ItemContainer, newItem: ItemContainer): Section<Nothing, Nothing, Nothing>? {
        for (section in sections) {
            if (section.value.hasItems(item, newItem)) return section.value
        }
        return null
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_FOOTER = 1
        private const val VIEW_TYPE_ITEM_LOADED = 2
        private const val VIEW_TYPE_LOADING = 3
        private const val VIEW_TYPE_FAILED = 4
        private const val VIEW_TYPE_EMPTY = 5
        private const val VIEW_TYPE_QTY = 6
    }

    private val sections: MutableMap<String, Section<Nothing, Nothing, Nothing>>

    private val sectionViewTypeNumbers: MutableMap<String, Int>

    private var viewTypeCount = 0

    init {
        sections = LinkedHashMap()
        sectionViewTypeNumbers = LinkedHashMap()
    }

    /**
     * Add a section
     *
     * @param key     unique key of the section
     * @param section section to be added
     */
    fun addSection(key: String, section: Section<Nothing, Nothing, Nothing>) {
        sections[key] = section
        sectionViewTypeNumbers[key] = viewTypeCount
        viewTypeCount += VIEW_TYPE_QTY
    }

    /**
     * Add a section with a random key.
     *
     * @param section section to be added
     * @return generated key
     */
    fun addSection(section: Section<*, *, *>): String {
        val key = UUID.randomUUID().toString()
        addSection(key, section as Section<Nothing, Nothing, Nothing>)
        notifyItemRangeInserted(getItemsCountInSections() + 1, section.itemsCount())
        return key
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Nothing> {
        var viewHolder: BaseViewHolder<Nothing>? = null

        for ((key, value) in sectionViewTypeNumbers) {
            if (viewType >= value && viewType < value + VIEW_TYPE_QTY) {

                val section = sections[key]
                val sectionViewType = viewType - value

                viewHolder = getViewHolderByType(sectionViewType, parent, section)
                break
            }
        }

        return viewHolder!!
    }

    override fun getItemCount(): Int {
        var count = 0

        sections.forEach {
            if (it.value.isVisible) count += it.value.itemsCount()
        }

        return count
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Nothing>, position: Int) {
        var currentPos = 0

        for ((_, section) in sections) {
            if (!section.isVisible) continue
            val sectionTotal = section.itemsCount()

            if (position >= currentPos && position <= currentPos + sectionTotal - 1) {

                if (section.hasHeader) {
                    if (position == currentPos) {
                        getSectionByPosition(position).onBindHeaderViewHolder(holder)
                        return
                    }
                }

                if (section.hasFooter) {
                    if (position == currentPos + sectionTotal - 1) {
                        getSectionByPosition(position).onBindFooterViewHolder(holder)
                        return
                    }
                }

                getSectionByPosition(position).onBindContentViewHolder(holder, getPositionInSection(position))
                return
            }

            currentPos += sectionTotal
        }
    }


    override fun getItemViewType(position: Int): Int {
        var currentPos = 0

        for ((key, section) in sections) {
            if (!section.isVisible) continue
            val sectionTotal = section.itemsCount()

            if (position >= currentPos && position <= currentPos + sectionTotal - 1) {

                val viewType = sectionViewTypeNumbers[key]!!

                if (section.hasHeader) {
                    if (position == currentPos) return viewType
                }

                if (section.hasFooter) {
                    if (position == currentPos + sectionTotal - 1) return viewType + 1
                }

                return when (section.state) {
                    State.LOADED -> viewType + VIEW_TYPE_ITEM_LOADED
                    State.LOADING -> viewType + VIEW_TYPE_LOADING
                    State.FAILED -> viewType + VIEW_TYPE_FAILED
                    State.EMPTY -> viewType + VIEW_TYPE_EMPTY
                }
            }

            currentPos += sectionTotal
        }
        throw IndexOutOfBoundsException("Invalid position")
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                if (results.values == null) notifyDataSetChanged()
                else dispatchUpdates((results.values as DiffUtil.DiffResult))
            }

            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterResult = FilterResults()

                val resultLists = ArrayList<Pair<List<ItemContainer>, List<ItemContainer>>>()

                for (section in sections) {
                    val result = section.value.filter(constraint)
                    result?.let { resultLists.add(result) }
                }

                if (resultLists.isEmpty()) filterResult.values = null else {

                    val oldList = ArrayList<ItemContainer>()
                    val newList = ArrayList<ItemContainer>()

                    for (pair in resultLists) {
                        oldList.addAll(pair.first)
                        newList.addAll(pair.second)
                    }

                    diffUtilItemCallback.submitLists(oldList, newList)
                    filterResult.values = DiffUtil.calculateDiff(diffUtilItemCallback)
                }

                return filterResult
            }
        }
    }

    private fun getSectionByPosition(position: Int): Section<Nothing, Nothing, Nothing> {
        var currentPos = 0

        for ((_, section) in sections) {

            if (!section.isVisible) continue

            val sectionTotal = section.itemsCount()

            if (position >= currentPos && position <= currentPos + sectionTotal - 1) return section

            currentPos += sectionTotal
        }

        throw IndexOutOfBoundsException("Invalid position")
    }

    private fun getItemsCountInSections(): Int {
        var count = 0
        sections.forEach {
            count += it.value.itemsCount()
        }
        return count
    }

    private fun getPositionInSection(position: Int): Int {
        var currentPos = 0

        for ((_, section) in sections) {

            if (!section.isVisible) continue

            val sectionTotal = section.itemsCount()

            if (position >= currentPos && position <= currentPos + sectionTotal - 1) {
                return position - currentPos
            }

            currentPos += sectionTotal
        }

        throw IndexOutOfBoundsException("Invalid position")
    }

    private fun dispatchUpdates(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)
    }

    private fun getPositionInAdapter(section: Section<Nothing, Nothing, Nothing>, position: Int): Int {
        return getSectionPosition(section) + (if (section.hasHeader) 1 else 0) + position
    }

    fun getSectionPosition(section: Section<Nothing, Nothing, Nothing>): Int {
        var currentPos = 0

        for ((_, loopSection) in sections) {

            if (!loopSection.isVisible) continue

            if (loopSection === section) return currentPos

            val sectionTotal = loopSection.itemsCount()

            currentPos += sectionTotal
        }

        throw IllegalArgumentException("Invalid section")
    }

    private fun getViewHolder(
        parent: ViewGroup,
        resourceId: Int?,
        getItemFunction: (view: View) -> BaseViewHolder<Nothing>
    ): BaseViewHolder<Nothing> {
        require(resourceId != null) { "Missing 'item' resource id" }
        return getItemFunction(inflate(resourceId!!, parent))
    }

    private fun getViewHolderByType(
        sectionViewType: Int,
        parent: ViewGroup,
        section: Section<Nothing, Nothing, Nothing>?
    ): BaseViewHolder<Nothing> {
        return when (sectionViewType) {
            VIEW_TYPE_HEADER ->
                getViewHolder(
                    parent,
                    section!!.headerResourceId,
                    section::getHeaderViewHolder
                )
            VIEW_TYPE_FOOTER ->
                getViewHolder(
                    parent,
                    section!!.footerResourceId,
                    section::getFooterViewHolder
                )

            VIEW_TYPE_ITEM_LOADED ->
                getViewHolder(
                    parent,
                    section!!.itemResourceId,
                    section::getItemViewHolder
                )

            VIEW_TYPE_LOADING ->
                getViewHolder(
                    parent,
                    section!!.loadingResourceId,
                    section::getLoadingViewHolder
                )

            VIEW_TYPE_FAILED ->
                getViewHolder(
                    parent,
                    section!!.failedResourceId,
                    section::getFailedViewHolder
                )

            VIEW_TYPE_EMPTY ->
                getViewHolder(
                    parent,
                    section!!.emptyResourceId,
                    section::getEmptyViewHolder
                )

            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    private fun inflate(@LayoutRes layoutResourceId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutResourceId, parent, false)
    }
}