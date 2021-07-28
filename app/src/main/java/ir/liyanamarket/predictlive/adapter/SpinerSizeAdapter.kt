package ir.liyanamarket.predictlive.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ir.liyanamarket.predictlive.R
import kotlinx.android.synthetic.main.spinersizeitem.view.*

class SpinerSizeAdapter:BaseAdapter() {
    lateinit var list: MutableList<String>
    override fun getCount(): Int {
return list.size
    }

    override fun getItem(p0: Int): String {
     return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
       val view=LayoutInflater.from(p2!!.context).inflate(R.layout.spinersizeitem,null)
        val data=getItem(p0)
        view.txt_sizetext.text=data
        return view


    }

}