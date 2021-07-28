package ir.liyanamarket.predictlive.presenter.match

import ir.liyanamarket.predictlive.`interface`.SendSelectMatchInterface
import ir.liyanamarket.predictlive.dataclass.Match
import ir.liyanamarket.predictlive.model.match.ApiConnectToSelectMatch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PresenterApiConnectSelectMatch:KoinComponent,Callback<List<Match>> {
    lateinit var sendSelectMatchInterface: SendSelectMatchInterface
    private val apiConnectToSelectMatch:ApiConnectToSelectMatch by inject()
    fun getmatch(matchdate:String)
    {
       apiConnectToSelectMatch.selectmatch().selectmatch("selectmatch",matchdate).enqueue(this)
    }

    override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {

            val data=response.body()
            if(data!=null)
            {
                sendSelectMatchInterface.onsuccessMatch(data)
            }


    }

    override fun onFailure(call: Call<List<Match>>, t: Throwable) {
      sendSelectMatchInterface.onerrorMatch(t)
    }
}