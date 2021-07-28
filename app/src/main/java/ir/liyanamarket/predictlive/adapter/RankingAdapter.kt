package ir.liyanamarket.predictlive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.Users
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RankingAdapter(val context: Context):RecyclerView.Adapter<RankingAdapter.Customviewholder>(),KoinComponent {
    lateinit var list:List<Users>
    private val picasso:Picasso by inject()
    inner class Customviewholder(itemview:View):RecyclerView.ViewHolder(itemview){
       val txtname: TextView = itemview.findViewById(R.id.name_rankingrecyclerlist)
        val txtscore: TextView =itemview.findViewById(R.id.score_recyclerranking)
        val txtrank: TextView =itemview.findViewById(R.id.rank_recyclerranking)
        val img: ImageView =itemview.findViewById(R.id.image_recyclerranking)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Customviewholder {



            val view = LayoutInflater.from(context)
                .inflate(R.layout.rankingrecyclerlist, parent, false)
            return Customviewholder(view)


    }

    override fun onBindViewHolder(holder: Customviewholder, position: Int) {
        holder.txtname.text = list[position].name
        holder.txtscore.text = list[position].Score.toString()
        val rank = position + 4
        holder.txtrank.text = rank.toString()
        picasso.load(list[position].image).fit().centerCrop().into(holder.img)
    }

    override fun getItemCount(): Int {
       return list.size
    }







}