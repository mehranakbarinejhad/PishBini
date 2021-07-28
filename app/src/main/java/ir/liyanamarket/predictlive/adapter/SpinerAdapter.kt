package ir.liyanamarket.predictlive.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.SpinerList
import kotlinx.android.synthetic.main.spinercustom.view.*

class SpinerAdapter:BaseAdapter() {
   lateinit var list: List<SpinerList>
    override fun getCount(): Int {
     return list.size
    }

    override fun getItem(p0: Int): SpinerList {
return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
        val view=LayoutInflater.from(parent!!.context).inflate(R.layout.spinercustom,null)
        val data:SpinerList=getItem(p0)
        view.txt_spineritem.text=data.name
        view.imagespineritem.setImageResource(data.image)
        return view

    }
}