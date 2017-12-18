package com.example.shiji_xc.headandfootrecycleview

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup

/**
 * Created by shiji-xc on 2017/11/6.
 */
class HeaderAndFooterWrapper(private val mInnerAdapter: RecyclerView.Adapter<ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val BASE_ITEM_TYPE_HEADER = 10000
        private val BASE_ITEM_TYPE_FOOTER = 20000
    }

    private val mHeadViews = SparseArrayCompat<View>()
    private val mFootViews = SparseArrayCompat<View>()

    private fun isHeadViewPos(position: Int): Boolean {
        return position < getHeadCount()
    }

    private fun isFootViewPos(position: Int): Boolean {
        return position >= getHeadCount() + getRealItemCount()
    }

    private fun getRealItemCount(): Int {
        return mInnerAdapter.itemCount
    }

    fun getHeadCount(): Int {
        return mHeadViews.size()
    }

    fun getFootViewCount(): Int {
        return mFootViews.size()
    }

    fun addHeadView(view: View) {
        mHeadViews.put(BASE_ITEM_TYPE_HEADER + mHeadViews.size(), view)
    }

    fun addFootView(view: View) {
        mFootViews.put(BASE_ITEM_TYPE_FOOTER + mFootViews.size(), view)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        return when {
            mHeadViews.get(viewType) != null -> ViewHolder.createViewHolder(parent!!.context, mHeadViews.get(viewType))
            mFootViews.get(viewType) != null -> ViewHolder.createViewHolder(parent!!.context, mFootViews.get(viewType))
            else -> mInnerAdapter.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        when {
            isHeadViewPos(position) -> return
            isFootViewPos(position) -> return
            else -> mInnerAdapter.onBindViewHolder(holder as ViewHolder?,position-getHeadCount())
        }
    }

    override fun getItemCount(): Int {
        return getHeadCount() + getRealItemCount() + getFootViewCount()
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeadViewPos(position)) {
            return mHeadViews.keyAt(position)
        } else if (isFootViewPos(position)) {
            return mFootViews.keyAt(position - getHeadCount() - getRealItemCount())
        }
        return mInnerAdapter.getItemViewType(position-getHeadCount())
    }

    /**
     * 用于处理gridLayout时的item问题
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView!!.layoutManager
        if(layoutManager is GridLayoutManager){
            layoutManager.spanSizeLookup=object : GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    val viewType = getItemViewType(position)
                    if(mHeadViews.get(viewType)!=null){
                        return layoutManager.spanCount
                    }else if(mFootViews.get(viewType)!=null){
                        return layoutManager.spanCount
                    }
                    return 1
                }
            }
        }
    }

    /**
     * 用于处理StaggeredGridLayoutManager时的item问题
     */
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder?) {
        mInnerAdapter.onViewAttachedToWindow(holder as ViewHolder?)
        val position = holder!!.layoutPosition
        if(isHeadViewPos(position)||isFootViewPos(position)){
            val layoutParams = holder.itemView.layoutParams
            if(layoutParams!=null&&layoutParams is StaggeredGridLayoutManager){
                layoutParams as StaggeredGridLayoutManager.LayoutParams
                layoutParams.isFullSpan=true
            }
        }
    }
}