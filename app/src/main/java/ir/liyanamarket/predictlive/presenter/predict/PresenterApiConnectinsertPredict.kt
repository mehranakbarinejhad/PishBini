package ir.liyanamarket.predictlive.presenter.predict

import ir.liyanamarket.predictlive.`interface`.SendinsertpredictInterface
import ir.liyanamarket.predictlive.dataclass.ResultPredict
import ir.liyanamarket.predictlive.model.predict.ApiConnectinsertPredict
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectinsertPredict:Callback<List<ResultPredict>>,KoinComponent {
    lateinit var sendinsertpredictInterface: SendinsertpredictInterface
    private val apiConnectinsertPredict:ApiConnectinsertPredict by inject()
    fun insertpredict(username:String,matchid:Int,hometeamgols:Int,guesteamgols:Int){
        apiConnectinsertPredict.insertpredict().insertpredict("insertorupdatepredict",username,matchid,hometeamgols,guesteamgols).enqueue(this)

    }

    override fun onResponse(
        call: Call<List<ResultPredict>>,
        response: Response<List<ResultPredict>>
    ) {
        val data=response.body()
        if(data!=null)
        {
            sendinsertpredictInterface.onsuccess(data)
        }
    }

    override fun onFailure(call: Call<List<ResultPredict>>, t: Throwable) {
        sendinsertpredictInterface.onerror(t)

    }


}