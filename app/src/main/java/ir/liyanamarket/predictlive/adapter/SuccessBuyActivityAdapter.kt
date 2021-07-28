package ir.liyanamarket.predictlive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.Buy
import java.text.DecimalFormat

class SuccessBuyActivityAdapter:RecyclerView.Adapter<SuccessBuyActivityAdapter.Customrecyclersuccessbuy>() {
    lateinit var list:List<Buy>
    inner class Customrecyclersuccessbuy(itemviw:View):RecyclerView.ViewHolder(itemviw){
        val codepeygiri:TextView=itemviw.findViewById(R.id.txt_codepeygiri_successbuyactivity1)
        val namekala:TextView=itemviw.findViewById(R.id.txt_namekala_successbuyactivity1)
        val tedad:TextView=itemviw.findViewById(R.id.txt_tedad_successbuyactivity1)
        val price:TextView=itemviw.findViewById(R.id.txt_gimat_successbuyactivity1)
        val sum:TextView=itemviw.findViewById(R.id.txt_sum_successbuyactivity1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Customrecyclersuccessbuy {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.successbasketitem,parent,false)
        return Customrecyclersuccessbuy(view)
    }

    override fun onBindViewHolder(holder: Customrecyclersuccessbuy, position: Int) {
            holder.codepeygiri.text=list[position].codesefaresh
            holder.namekala.text=list[position].namekala
            holder.tedad.text=list[position].tedadkala
            holder.price.text=DecimalFormat("###,###,###").format(list[position].pricekala.toLong())
            val sum=((list[position].tedadkala.toInt())*(list[position].pricekala.toInt())).toString()
        holder.sum.text= DecimalFormat("###,###,###").format(sum.toLong())

    }

    override fun getItemCount(): Int {
   return list.size
    }
}