package ir.liyanamarket.predictlive.presenter.footballapi.topscores

import ir.liyanamarket.predictlive.`interface`.footballapi.SendSelectTopScores
import ir.liyanamarket.predictlive.dataclass.apifootball.topscores.TopScores
import ir.liyanamarket.predictlive.model.footballapi.topscores.ApiConnectSelectTopScores
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSelectTopScores:KoinComponent,Callback<TopScores> {
    private val apiConnectSelectTopScores: ApiConnectSelectTopScores by inject()
    lateinit var sendSelectTopScores: SendSelectTopScores
    fun selecttopscore(codeleague:String)
    {
        apiConnectSelectTopScores.select().selecttopscore("selecttopscore",codeleague).enqueue(this)
    }

    override fun onResponse(call: Call<TopScores>, response: Response<TopScores>) {
        val data=response.body()
        if(data!=null)
        {
            sendSelectTopScores.onsuccesstopscores(data)
        }
    }

    override fun onFailure(call: Call<TopScores>, t: Throwable) {
       sendSelectTopScores.onerror(t)
    }
}