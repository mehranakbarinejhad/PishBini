package ir.liyanamarket.predictlive.presenter.predict

import ir.liyanamarket.predictlive.`interface`.SendSelectPredictInterface
import ir.liyanamarket.predictlive.dataclass.Predict
import ir.liyanamarket.predictlive.model.predict.ApiConnectSelectPredict
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSelectPredict:KoinComponent,Callback<List<Predict>> {
    lateinit var sendSelectPredictInterface:SendSelectPredictInterface
private val apiConnectSelectPredict:ApiConnectSelectPredict by inject()
    fun selectPredict(username:String)
    {
    apiConnectSelectPredict.selectpredict().selectpredicuser("selectpredictbyuser",username).enqueue(this)
    }

    override fun onResponse(call: Call<List<Predict>>, response: Response<List<Predict>>) {
        val data=response.body()
        if(data!=null)
        {
            sendSelectPredictInterface.onsuccessPredict(data)
        }
    }

    override fun onFailure(call: Call<List<Predict>>, t: Throwable) {
            sendSelectPredictInterface.onerrorPredict(t)
    }
}