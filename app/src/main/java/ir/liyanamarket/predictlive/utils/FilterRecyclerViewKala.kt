package ir.liyanamarket.predictlive.utils

import androidx.recyclerview.widget.RecyclerView
import ir.liyanamarket.predictlive.dataclass.Kala

class FilterRecyclerViewKala {
    fun filter(filtertext:String,arraylist:ArrayList<Kala>,displaylist:ArrayList<Kala>,recyclerview:RecyclerView){

        if(filtertext.isNotEmpty())
        {
           displaylist.clear()
            arraylist.forEach {
                val completekalaname=it.titr+" "+it.name

                if(completekalaname.contains(filtertext))

                {
                    displaylist.add(it)
                }
            }
            recyclerview.adapter!!.notifyDataSetChanged()
        }
        else
        {
            displaylist.clear()
            recyclerview.adapter!!.notifyDataSetChanged()

        }

    }
}