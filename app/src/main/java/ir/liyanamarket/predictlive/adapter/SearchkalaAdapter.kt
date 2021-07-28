package ir.liyanamarket.predictlive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.Kala

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchkalaAdapter(private val context:Context):RecyclerView.Adapter<SearchkalaAdapter.CustomViewHolder>(),KoinComponent {
    private val picasso:Picasso by inject()
    lateinit var list:MutableList<Kala>
    inner class CustomViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val imgkala:ImageView=itemview.findViewById(R.id.img_kala_searchactivity)
        val txttitr:TextView=itemview.findViewById(R.id.txt_titrkala_searchactivity)
        val txtname:TextView=itemview.findViewById(R.id.txt_name_searchactivity)
        val txtprice:TextView=itemview.findViewById(R.id.txt_price_searchactivity)
        val parent:RelativeLayout=itemview.findViewById(R.id.parent_recyclersearchactivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.searchrecycleritem,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.parent.animation=AnimationUtils.loadAnimation(context.applicationContext,R.anim.moveright)
       picasso.load(list[position].image).fit().into(holder.imgkala)
        holder.txttitr.text=list[position].titr
        holder.txtname.text=list[position].name
        holder.txtprice.text=list[position].price
    }

    override fun getItemCount(): Int {
      return list.size
    }
}