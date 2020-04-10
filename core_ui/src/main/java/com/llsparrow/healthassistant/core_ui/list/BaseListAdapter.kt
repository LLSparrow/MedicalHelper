package com.llsparrow.healthassistant.core_ui.list

import android.text.Editable
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.llsparrow.healthassistant.core_ui.view.viewmodel.BaseViewModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseListAdapter<VM : BaseViewModel> :
    RecyclerView.Adapter<BaseViewHolder<Any>>(), CoroutineScope {

    init {
        setHasStableIds(true)
    }

    var viewModel: VM? = null

    protected val viewBinders = mutableMapOf<ItemClass, ItemBinder>()

    private var recyclerView: RecyclerView? = null

    private val logExceptionHandler = CoroutineExceptionHandler(::showError)

    private var baseList = ArrayList<Any>()

    private var currentList = ArrayList<Any>()

    private val itemDiffCallback: DiffListUtil by lazy(LazyThreadSafetyMode.NONE) {
        DiffListUtil(
            viewBinders
        )
    }

    private val viewTypeToBinders by lazy(LazyThreadSafetyMode.NONE) {
        viewBinders.mapKeys { it.value.getItemType() }
    }

    override val coroutineContext: CoroutineContext =
        SupervisorJob() + Dispatchers.Default + logExceptionHandler

    protected abstract fun provideBindingComponent(
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding

    protected open fun filterConstraint(item: Any, str: String): Boolean = true

    fun submitItems(list: List<Any>) {
        if (baseList.isEmpty()) {
            currentList = ArrayList(list)
            baseList = currentList
            notifyDataSetChanged()
        } else {
            launch {
                itemDiffCallback.submitLists(currentList, list)
                dispatchUpdates(DiffUtil.calculateDiff(itemDiffCallback))
                currentList = ArrayList(list)
                baseList = currentList
            }
        }
    }

    fun filterText(search: Editable) {
        launch {
            if (baseList.isNullOrEmpty()) return@launch

            val str = search.toString()
            var filteredList = ArrayList<Any>()

            if (str.isEmpty()) {
                filteredList = baseList
            } else {
                for (item in baseList) {

                    if (filterConstraint(item, str)) {
                        filteredList.add(item)
                    }
                }
            }

            itemDiffCallback.submitLists(currentList, filteredList)
            currentList = filteredList
            dispatchUpdates(DiffUtil.calculateDiff(itemDiffCallback))
        }
    }

    fun isEmpty(): Boolean = currentList.isEmpty()

    final override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onFailedToRecycleView(holder: BaseViewHolder<Any>): Boolean {
        return true
    }

    override fun getItemId(position: Int): Long {
        return (currentList[position] as ListItem).ITEM_CONTAINER_ID
    }

    override fun getItemCount(): Int = currentList.size

    override fun getItemViewType(position: Int): Int {
        return viewBinders.getValue(currentList[position].javaClass).getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return getViewBinder(viewType).createViewHolder(provideBindingComponent(parent, viewType))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Any? {
        return currentList[position]
    }

    private suspend fun dispatchUpdates(result: DiffUtil.DiffResult) {
        withContext(Dispatchers.Main) {
            recyclerView?.scrollToPosition(0)
            result.dispatchUpdatesTo(this@BaseListAdapter)
        }
    }

    private fun showError(context: CoroutineContext, throwable: Throwable) {
        Logger.e(throwable, throwable.localizedMessage ?: "")
    }

    private fun getViewBinder(viewType: Int): ItemBinder = viewTypeToBinders.getValue(viewType)
}

