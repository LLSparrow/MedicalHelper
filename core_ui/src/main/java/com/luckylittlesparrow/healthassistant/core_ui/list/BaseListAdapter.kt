package com.luckylittlesparrow.healthassistant.core_ui.list

import android.text.Editable
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.luckylittlesparrow.healthassistant.core_ui.view.viewmodel.BaseViewModel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseListAdapter<T : ListItem, VM : BaseViewModel> : RecyclerView.Adapter<ListViewHolder<T>>(), CoroutineScope {

    init {
        setHasStableIds(true)
    }

    var viewModel: VM? = null

    private var recyclerView: RecyclerView? = null

    private val logExceptionHandler = CoroutineExceptionHandler(::showError)

    private var baseList = ArrayList<T>()

    private var currentList = ArrayList<T>()

    private val itemDiffCallback: DiffListUtil<T> by lazy(LazyThreadSafetyMode.NONE) { DiffListUtil(diffCallback) }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Default + logExceptionHandler

    abstract val diffCallback: DiffUtil.ItemCallback<T>

    abstract fun provideBindingComponent(parent: ViewGroup, viewType: Int): ViewDataBinding

    abstract fun filterConstraint(item: T, str: String): Boolean

    abstract fun provideViewHolder(binding: ViewDataBinding): ListViewHolder<T>

    fun submitItems(list: List<T>) {
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
            var filteredList = ArrayList<T>()

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
        recyclerView.recycledViewPool.setMaxRecycledViews(getItemViewType(0), 20)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onFailedToRecycleView(holder: ListViewHolder<T>): Boolean {
        return true
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].ITEM_CONTAINER_ID
    }

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<T> {
        return provideViewHolder(provideBindingComponent(parent, viewType))
    }

    override fun onBindViewHolder(holder: ListViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): T? {
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
}

abstract class ListViewHolder<T>(
    protected val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T?)
}