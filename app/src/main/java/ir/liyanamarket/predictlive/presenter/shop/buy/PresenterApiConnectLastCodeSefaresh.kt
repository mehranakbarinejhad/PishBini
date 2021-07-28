package ir.liyanamarket.predictlive.presenter.shop.buy

import ir.liyanamarket.predictlive.`interface`.SendLastCodeSefaresh
import ir.liyanamarket.predictlive.dataclass.ResultCodeSefaresh
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectLastCodesefaresh
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectLastCodeSefaresh:KoinComponent,Callback<List<ResultCodeSefaresh>> {
    private val apiconnectlastcodesefaresh:ApiConnectLastCodesefaresh by inject()
    lateinit var sendLastCodeSefaresh: SendLastCodeSefaresh
    fun selectlast(){
        apiconnectlastcodesefaresh.selectlast().selectlast("selectlastcodesefaresh").enqueue(this)
    }

    override fun onResponse(
        call: Call<List<ResultCodeSefaresh>>,
        response: Response<List<ResultCodeSefaresh>>
    ) {
        val data=response.body()
        if(data!=null)
        {
            sendLastCodeSefaresh.onsuccesslastcode(data)
        }
    }

    override fun onFailure(call: Call<List<ResultCodeSefaresh>>, t: Throwable) {
        sendLastCodeSefaresh.onerrorlastcode(t)
    }
}