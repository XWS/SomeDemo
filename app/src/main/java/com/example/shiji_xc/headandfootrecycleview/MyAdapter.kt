package com.example.shiji_xc.headandfootrecycleview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by shiji-xc on 2017/11/7.
 */
open class MyAdapter<in T>(private val list:List<T>, private val layoutId:Int) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder.createViewHolder(parent!!.context,parent,layoutId)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        convert(holder!!, list[position])
    }

    open fun convert(holder : ViewHolder,t : T){

    }
}