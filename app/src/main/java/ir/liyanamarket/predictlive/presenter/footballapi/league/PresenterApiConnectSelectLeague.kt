package ir.liyanamarket.predictlive.presenter.footballapi.league

import ir.liyanamarket.predictlive.`interface`.footballapi.SendSelectLeague
import ir.liyanamarket.predictlive.dataclass.apifootball.league.League
import ir.liyanamarket.predictlive.model.footballapi.league.ApiConnectSelectLeague
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSelectLeague:KoinComponent,Callback<List<League>> {
    lateinit var sendSelectLeague: SendSelectLeague
    private val apiConnectSelectLeague:ApiConnectSelectLeague by inject()
    fun selectgroupleague(){
        apiConnectSelectLeague.selectleague().selectgroupleague("selectgroupleague").enqueue(this)
    }

    override fun onResponse(call: Call<List<League>>, response: Response<List<League>>) {
        val data=response.body()
        if(data!=null)
        {
            sendSelectLeague.onsuccessleague(data)
        }
    }

    override fun onFailure(call: Call<List<League>>, t: Throwable) {
       sendSelectLeague.onerrorleague(t)
    }
}