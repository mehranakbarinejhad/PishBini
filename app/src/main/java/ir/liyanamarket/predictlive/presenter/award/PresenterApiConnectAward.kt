package ir.liyanamarket.predictlive.presenter.award

import ir.liyanamarket.predictlive.`interface`.SendAwardText
import ir.liyanamarket.predictlive.dataclass.Award
import ir.liyanamarket.predictlive.model.award.ApiconnectAwardservices
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectAward:KoinComponent,Callback<Award> {
    private val apiconnectAwardservices:ApiconnectAwardservices by inject()
    lateinit var sendAwardText: SendAwardText
    fun showtext(){
       apiconnectAwardservices.showtext().showtext("selectawardtext").enqueue(this)
    }

    override fun onResponse(call: Call<Award>, response: Response<Award>) {
       val data=response.body()
        if(data!=null)
        {
            sendAwardText.onsuccess(data)
        }
    }

    override fun onFailure(call: Call<Award>, t: Throwable) {
      sendAwardText.onerror(t)
    }
}