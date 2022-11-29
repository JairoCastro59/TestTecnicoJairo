package com.example.material.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.material.extensionFunctions.isLastItem

abstract class BaseAdapter<M, VH : BaseHolder> : RecyclerView.Adapter<VH>() {

    interface OnItemClickListener<M> {
        fun onItemClick(view: View, entity: M, position: Int)
    }

    interface OnChangeDataSetListener {
        fun onChange()
    }

    interface OnLoadDataListener {
        fun onLoadComplete()
    }

    lateinit var context: Context
    var currentSelection: Pair<View, M>? = null
    var dataSet: ArrayList<M>? = null
    var currentPositionSelection: Int? = null
    var onItemClickListener: OnItemClickListener<M>? = null
    var onLoadDataListener: OnLoadDataListener? = null
    private var isEnabled: Boolean = true
    private var onChangeDataSetListener: OnChangeDataSetListener? = null

    @LayoutRes
    abstract fun getRowViewResourceId(viewType: Int): Int

    abstract fun createHolder(view: View, viewType: Int): VH
    abstract fun bindViewHolder(holder: VH, item: M)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        val cardView = LayoutInflater.from(parent.context)
            .inflate(getRowViewResourceId(viewType), parent, false)
        return createHolder(cardView, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val holderPosition: Int = holder.adapterPosition
        holder.itemView.isEnabled = isEnabled
        val item = dataSet?.get(holderPosition)
        if (item != null) {
            bindViewHolderDefaultState(holder)
            bindViewHolder(holder, item)
            holder.itemView.setOnClickListener {
                onItemSelected(
                    holder.itemView,
                    item,
                    holderPosition
                )
            }

            if (isFirstItem(holderPosition)) onLoadDataListener?.onLoadComplete()
        }
    }

    protected open fun onItemSelected(itemView: View, item: M, adapterPosition: Int) {
        if (!selectionOfItemIsAllowed(itemView, item, adapterPosition)) {
            currentSelection = Pair(itemView, item)
            onItemClickListener?.onItemClick(itemView, item, adapterPosition)
        } else {
            currentSelection?.let { hideHighlightOfSelectedItem(it.first, it.second) }
            currentSelection = Pair(itemView, item)
            highlightSelectedItem(itemView, item, adapterPosition)
            onItemClickListener?.onItemClick(itemView, item, adapterPosition)
        }
    }

    protected open fun selectionOfItemIsAllowed(
        itemView: View,
        item: M,
        adapterPosition: Int
    ): Boolean {
        return true
    }

    protected open fun changePositionSelection(itemView: View, item: M, newPositionSelected: Int) {
        currentSelection?.let { hideHighlightOfSelectedItem(it.first, it.second) }
        currentSelection = Pair(itemView, item)
        highlightSelectedItem(itemView, item, newPositionSelected)
    }

    open fun changeDataSet(dataSet: ArrayList<M>?) {
        this.dataSet = dataSet
        notifyDataSetChanged()
        onChangeDataSetListener?.onChange()
    }

    protected fun isLastItem(position: Int): Boolean {
        return dataSet?.isLastItem(position) ?: false
    }

    protected fun isFirstItem(position: Int): Boolean {
        return position == 0
    }

    protected open fun isEnabled(): Boolean = isEnabled

    protected open fun highlightSelectedItem(itemView: View, item: M, adapterPosition: Int) {}

    protected open fun hideHighlightOfSelectedItem(itemView: View, item: M) {}

    open fun bindViewHolderDefaultState(holder: VH) {}

    open fun clearSelection() {}

}