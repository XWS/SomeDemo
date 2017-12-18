package com.example.shiji_xc.headandfootrecycleview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView

/**
 * Created by shiji-xc on 2017/11/6.
 */
class RecycleViewActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        initView()
    }

    private fun initView(){

        val items = listOf("1","2","3","4","5")

        val myAdapter = object : MyAdapter<String>(items, R.layout.item_layout){

            override fun convert(holder: ViewHolder, t: String) {
                holder.getView<TextView>(R.id.tv).text = t
            }
        }

        val wrapper = HeaderAndFooterWrapper(myAdapter)

        val view = TextView(this)
        view.text="6666"
        wrapper.addHeadView(view)

        val view1 = TextView(this)
        view1.text="7777"
        wrapper.addHeadView(view1)

        val view2 = TextView(this)
        view2.text="8888"
        wrapper.addFootView(view2)

        val view3 = TextView(this)
        view3.text="9999"
        wrapper.addFootView(view3)

        val recycleView = findViewById<RecyclerView>(R.id.recycle)
        //recycleView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycleView.layoutManager=GridLayoutManager(this,2)
        recycleView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recycleView.adapter=wrapper
    }
}
