package ir.liyanamarket.predictlive.adapter.topscore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.apifootball.topscores.Player
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TopScoreAdapter:RecyclerView.Adapter<TopScoreAdapter.CustomHolderTopScore>(),KoinComponent {
    private val picasso:Picasso by inject()
    lateinit var list:List<Player>
    inner class CustomHolderTopScore(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val imgplayer:ImageView=itemview.findViewById(R.id.img_player_topscores)
        val playername:TextView=itemview.findViewById(R.id.name_player_topscore)
        val parenttopscore:RelativeLayout=itemview.findViewById(R.id.parent_topscore)
        val goalsplayer:TextView=itemview.findViewById(R.id.goals_player_topscore)
        val imgteamlogo:ImageView=itemview.findViewById(R.id.img_teamlogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolderTopScore {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.topscorerecycleritems,parent,false)
        return CustomHolderTopScore(view)
    }

    override fun onBindViewHolder(holder: CustomHolderTopScore, position: Int) {

       picasso.load(list[position].player.photo).fit().into(holder.imgplayer)
        holder.playername.text=list[position].player.name
        holder.goalsplayer.text=list[position].statistics[0].goals.total.toString()
        picasso.load(list[position].statistics[0].team.logo).fit().into(holder.imgteamlogo)
        when((1..5).random()) {
            1 ->holder.parenttopscore.setBackgroundResource(R.drawable.shaperecyclerkalaitemgreen)
            2->holder.parenttopscore.setBackgroundResource(R.drawable.shaperecyclerkalaitemblue)
            3->holder.parenttopscore.setBackgroundResource(R.drawable.shaperecyclerkalaitemdark)
            4->holder.parenttopscore.setBackgroundResource(R.drawable.shaperecyclerkalaitempink)
           5->holder.parenttopscore.setBackgroundResource(R.drawable.shaperecyclerkalaitembrown)

        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

}