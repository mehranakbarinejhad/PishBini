package ir.liyanamarket.predictlive.adapter.topscore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.footballapi.SendSelectTopScores
import ir.liyanamarket.predictlive.dataclass.apifootball.league.League
import ir.liyanamarket.predictlive.dataclass.apifootball.topscores.TopScores
import ir.liyanamarket.predictlive.fragment.FragmentProgressBar
import ir.liyanamarket.predictlive.presenter.footballapi.topscores.PresenterApiConnectSelectTopScores
import ir.liyanamarket.predictlive.utils.MyMessage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShowLeagueAdapter(var context:Context):RecyclerView.Adapter<ShowLeagueAdapter.Customholdershowleague>(),KoinComponent,SendSelectTopScores {
    private val presenterApiConnectSelectTopScores:PresenterApiConnectSelectTopScores by inject()
    lateinit var list:List<League>
    private val picasso:Picasso by inject()
    lateinit var recyclerView: RecyclerView
    private val topScoreAdapter:TopScoreAdapter by inject()
    private val progressBar:FragmentProgressBar by inject()
    lateinit var activity:AppCompatActivity
    private val fragmentmessagegroupadapter:MyMessage by inject()
    private var index:Int=0
    inner class Customholdershowleague(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val txtnameleague:TextView=itemview.findViewById(R.id.txt_leaguename_showleagueadapter)
        val imgleague:ImageView=itemview.findViewById(R.id.img_league_topscoreactivity)
        val parentgroup:RelativeLayout=itemview.findViewById(R.id.parent_group_topscore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Customholdershowleague {
val view=LayoutInflater.from(parent.context).inflate(R.layout.showleaguerecycleritem,parent,false)
    return Customholdershowleague(view)
    }

    override fun onBindViewHolder(holder: Customholdershowleague, position: Int) {

holder.txtnameleague.text=list[position].name
        picasso.load(list[position].logo).fit().into(holder.imgleague)


        holder.parentgroup.setOnClickListener {
            fragmentmessagegroupadapter.activity=activity
           presenterApiConnectSelectTopScores.sendSelectTopScores=this
            progressBar.show(activity.supportFragmentManager,"showprogressgroup")
           presenterApiConnectSelectTopScores.selecttopscore(list[position].code)
            index=position
            notifyDataSetChanged()

        }
        if(index==position)
        {
            holder.parentgroup.setBackgroundResource(R.drawable.shaperecyclerkalaitempink)
        }
        else
        {
            holder.parentgroup.setBackgroundResource(R.drawable.shaperecyclerkalaitemblue)


        }


    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onsuccesstopscores(list: TopScores) {
        topScoreAdapter.list=list.response
        recyclerView.apply {
            layoutManager=
                LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter=topScoreAdapter

        }
       progressBar.dismiss()
    }

    override fun onerror(t: Throwable) {

        progressBar.dismiss()
        fragmentmessagegroupadapter.show("لطفا وضعیت اینترنت را بررسی کنید و دوباره تلاش کنید")

    }
}