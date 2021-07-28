package ir.liyanamarket.predictlive.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.footballapi.SendSelectLeague
import ir.liyanamarket.predictlive.`interface`.footballapi.SendSelectTopScores
import ir.liyanamarket.predictlive.adapter.topscore.ShowLeagueAdapter
import ir.liyanamarket.predictlive.adapter.topscore.TopScoreAdapter
import ir.liyanamarket.predictlive.dataclass.apifootball.league.League
import ir.liyanamarket.predictlive.dataclass.apifootball.topscores.TopScores
import ir.liyanamarket.predictlive.fragment.FragmentProgressBar
import ir.liyanamarket.predictlive.presenter.footballapi.league.PresenterApiConnectSelectLeague
import ir.liyanamarket.predictlive.presenter.footballapi.topscores.PresenterApiConnectSelectTopScores
import ir.liyanamarket.predictlive.utils.MyMessage
import kotlinx.android.synthetic.main.activity_top_scores.*
import org.koin.android.ext.android.inject

class TopScoresActivity : AppCompatActivity(),SendSelectLeague,SendSelectTopScores{
    private val presenterApiConnectSelectTopScores: PresenterApiConnectSelectTopScores by inject()
    private val presenterApiConnectSelectLeague:PresenterApiConnectSelectLeague by inject()
    private val showLeagueAdapter:ShowLeagueAdapter by inject()
  private val topScoreAdapter: TopScoreAdapter by inject()
    private val progressbarload:FragmentProgressBar by inject()
    private val mymessage:MyMessage by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_scores)
            presenterApiConnectSelectLeague.sendSelectLeague=this
     showLeagueAdapter.recyclerView=recycler_topscore
        presenterApiConnectSelectLeague.selectgroupleague()
        showLeagueAdapter.activity=this
        mymessage.activity=this

       presenterApiConnectSelectTopScores.sendSelectTopScores=this
    progressbarload.show(supportFragmentManager,"loadSeriA")
    }

    override fun onsuccesstopscores(list: TopScores) {
        topScoreAdapter.list=list.response
        recycler_topscore.apply {
            layoutManager=LinearLayoutManager(this@TopScoresActivity,LinearLayoutManager.VERTICAL,false)
            adapter=topScoreAdapter

        }
        progressbarload.dismiss()
    }

    override fun onerror(t: Throwable) {

        progressbarload.dismiss()
        mymessage.show("لطفا وضعیت اینترنت را بررسی کنید.")
    }

    override fun onsuccessleague(list: List<League>) {
        showLeagueAdapter.list=list
        recycler_league_topscoreactivity.apply {
            layoutManager=LinearLayoutManager(this@TopScoresActivity,LinearLayoutManager.HORIZONTAL,true)
            adapter=showLeagueAdapter
        }
        progressbarload.dismiss()
        presenterApiConnectSelectTopScores.selecttopscore("135")


    }

    override fun onerrorleague(t: Throwable) {
        progressbarload.dismiss()
        mymessage.show("لطفا وضعیت اینترنت را بررسی کنید.")


    }
}