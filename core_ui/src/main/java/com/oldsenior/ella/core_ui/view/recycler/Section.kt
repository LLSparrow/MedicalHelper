package com.oldsenior.ella.core_ui.view.recycler


import android.view.View
import androidx.annotation.LayoutRes

abstract class Section<H, I, F> : Filterable {

    companion object {
        private const val HEADER_POSITION = 0
    }

    enum class State {
        LOADING,
        LOADED,
        FAILED,
        EMPTY
    }

    /**
     * The current State of this Section.
     *
     * @return current state of this section
     */
    var state = State.LOADED
        set(state) {
            when (state) {
                State.LOADING -> require(loadingResourceId == null)
                { "Resource id for 'loading state' should be provided" }

                State.FAILED -> require(failedResourceId == null)
                { "Resource id for 'failed state' should be provided" }

                State.EMPTY -> require(emptyResourceId == null)
                { "Resource id for 'empty state' should be provided" }

                State.LOADED -> require(itemResourceId == null)
                { "Resource id for 'loaded state' should be provided" }
            }

            field = state
        }

    private val sourceList = ArrayList<ItemContainer>()
    private var baseList = ArrayList<ItemContainer>()
    private val filteredList = ArrayList<ItemContainer>()

    private var lastSearchString = ""
    private var shouldFilterHeader = false

    @LayoutRes
    var itemResourceId: Int? = null
        private set
    @LayoutRes
    var headerResourceId: Int? = null
        private set
    @LayoutRes
    var footerResourceId: Int? = null
        private set
    @LayoutRes
    var loadingResourceId: Int? = null
        private set
    @LayoutRes
    var failedResourceId: Int? = null
        private set
    @LayoutRes
    var emptyResourceId: Int? = null
        private set

    var isVisible = true
    var hasHeader: Boolean = false
    var hasFooter: Boolean = false

    init {
        init()
    }

    private fun init() {
        itemResourceId = getSectionParams().itemResourceId
        headerResourceId = getSectionParams().headerResourceId
        footerResourceId = getSectionParams().footerResourceId
        loadingResourceId = getSectionParams().loadingResourceId
        failedResourceId = getSectionParams().failedResourceId
        emptyResourceId = getSectionParams().emptyResourceId
        headerResourceId?.let { hasHeader = true }
        footerResourceId?.let { hasFooter = true }

        shouldFilterHeader = getSectionParams().shouldFilterHeader
    }

    lateinit var itemClickListener: (ItemContainer) -> Unit
        private set

    lateinit var headerClickListener: (ItemContainer) -> Unit
        private set

    lateinit var footerClickListener: (ItemContainer) -> Unit
        private set

    abstract fun getSectionParams(): SectionParams

    abstract fun getDiffUtilItemCallback(): BaseDiffUtilItemCallback

    fun addContentItems(list: List<ItemContainer>, clickListener: (ItemContainer) -> Unit = {}) {
        check(!hasHeader || hasHeader && sourceList.isNotEmpty()) { "submit header item first for better optimization" }
        if (hasFooter && sourceList.last().isFooter()) addMoreItems(list) else {
            sourceList.addAll(list)
            baseList.addAll(list)
            itemClickListener = clickListener
        }
    }

    fun addHeaderItem(item: ItemContainer, clickListener: (ItemContainer) -> Unit = {}) {
        headerClickListener = clickListener
        sourceList.add(HEADER_POSITION, item)
        baseList.add(HEADER_POSITION, item)
    }

    fun addFooterItem(item: ItemContainer, clickListener: (ItemContainer) -> Unit = {}) {
        footerClickListener = clickListener
        sourceList.add(getFooterIndex() + 1, item)
        baseList.add(getFooterIndex(), item)
    }

    internal fun onBindContentViewHolder(holder: BaseViewHolder<I>, position: Int) {
        when (state) {
            State.LOADING -> onBindLoadingViewHolder(holder)
            State.LOADED -> onBindItemViewHolder(holder, position)
            State.FAILED -> onBindFailedViewHolder(holder)
            State.EMPTY -> onBindEmptyViewHolder(holder)
        }
    }

    internal fun itemsCount(): Int {
        return if (filteredList.isEmpty()) baseList.size else filteredList.size
    }

    abstract fun getItemViewHolder(view: View): BaseViewHolder<I>

    open fun getHeaderViewHolder(view: View): BaseViewHolder<H> {
        check(hasHeader) { "Forgot to override HeaderViewHolder getter" }
        return EmptyViewHolder(view)
    }

    open fun getFooterViewHolder(view: View): BaseViewHolder<F> {
        check(hasFooter) { "Forgot to override FooterViewHolder getter" }
        return EmptyViewHolder(view)
    }

    open fun getLoadingViewHolder(view: View): BaseViewHolder<Nothing> {
        check(loadingResourceId != null) { "Forgot to override LoadingViewHolder getter" }
        return EmptyViewHolder(view)
    }

    open fun getEmptyViewHolder(view: View): BaseViewHolder<Nothing> {
        check(loadingResourceId != null) { "Forgot to override EmptyViewHolder getter" }
        return EmptyViewHolder(view)
    }

    open fun getFailedViewHolder(view: View): BaseViewHolder<Nothing> {
        check(loadingResourceId != null) { "Forgot to override FailedViewHolder getter" }
        return EmptyViewHolder(view)
    }

    open fun onBindHeaderViewHolder(holder: BaseViewHolder<H>) {
        holder.bindItem(sourceList.first().value() as H)
    }

    open fun onBindFooterViewHolder(holder: BaseViewHolder<F>) {
        holder.bindItem(sourceList.last().value() as F)
    }

    private fun onBindItemViewHolder(holder: BaseViewHolder<I>, position: Int) {
        holder.bindItem(getContentItem(position))
    }

    private fun onBindLoadingViewHolder(holder: BaseViewHolder<I>) {
        holder.bindLoadingView()
    }

    private fun onBindFailedViewHolder(holder: BaseViewHolder<I>) {
        holder.bindFailedView()
    }

    private fun onBindEmptyViewHolder(holder: BaseViewHolder<I>) {
        holder.bindEmptyView()
    }

    private fun addMoreItems(list: List<ItemContainer>) {
        val footerItem = sourceList.removeAt(getFooterIndex())
        sourceList.addAll(list)
        sourceList.add(footerItem)
        baseList.removeAt(getFooterIndex())
        baseList.addAll(list)
        baseList.add(footerItem)
    }

    /**
     * @param position position of requested item
     * @return item by position
     */
    private fun getContentItem(position: Int): I {
        return if (filteredList.isEmpty()) baseList[position] as I else filteredList[position] as I
    }

    private fun getFooterIndex(): Int = sourceList.size - 1

    /* Filter */

    abstract fun itemFilter(search: String, item: ItemContainer): Boolean

    abstract fun headerFilter(search: String, item: ItemContainer): Boolean

    internal fun areItemsTheSame(oldItem: ItemContainer, newItem: ItemContainer): Boolean {
        return if (!oldItem.value().isTheSameType(newItem.itemType)) false
        else
            when (oldItem.value().itemType) {
                ItemContainer.ItemType.ITEM -> getDiffUtilItemCallback().areItemsTheSame(
                    oldItem,
                    newItem
                )
                else -> oldItem == newItem
            }
    }

    internal fun areContentsTheSame(oldItem: ItemContainer, newItem: ItemContainer): Boolean {
        return if (!oldItem.value().isTheSameType(newItem.itemType)) false
        else
            when (oldItem.value().itemType) {
                ItemContainer.ItemType.ITEM -> getDiffUtilItemCallback().areContentsTheSame(
                    oldItem,
                    newItem
                )
                else -> oldItem.value() == newItem.value()
            }
    }


    override fun filter(search: CharSequence): Pair<List<ItemContainer>, List<ItemContainer>>? {
        val searchString = search.toString().toLowerCase()
        lastSearchString = searchString
        var isEmpty = true
        var startIndex = 0

        baseList = if (filteredList.isEmpty()) ArrayList(sourceList) else
            ArrayList(filteredList)

        filteredList.clear()

        if (hasHeader && shouldFilterHeader) {
            if (headerFilter(searchString, sourceList.first())) {
                filteredList.addAll(sourceList)
                return Pair(baseList, filteredList)
            }

            filteredList.add(sourceList.first())
            startIndex = 1
        }

        for (i in startIndex until sourceList.size) {
            if (hasFooter && i == getFooterIndex()) {
                filteredList.add(sourceList.last())
                break
            }

            if (itemFilter(searchString, sourceList[i])) {
                filteredList.add(sourceList[i])
                isEmpty = false
            }
        }
        isVisible = if (isEmpty) {
            filteredList.clear()
            false
        } else {
            true
        }

        if (baseList.size == filteredList.size) return null
        return Pair(baseList, filteredList)
    }

    fun hasItems(item: ItemContainer, newItem: ItemContainer): Boolean {
        return baseList.any { it === item } && baseList.any { it === newItem }
    }


    /* Filter */
}