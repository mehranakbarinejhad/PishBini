package ir.liyanamarket.predictlive.presenter.shop.buy

import ir.liyanamarket.predictlive.`interface`.ApiServicesSuccessBuy
import ir.liyanamarket.predictlive.`interface`.SendSelectSuccessBuy
import ir.liyanamarket.predictlive.dataclass.Buy
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectSelectsuccessBuy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterSelectSuccessBuy:Callback<List<Buy>>,KoinComponent {
    lateinit var sendSelectSuccessBuy: SendSelectSuccessBuy
private val apiConnectSelectsuccessBuy:ApiConnectSelectsuccessBuy by inject()

    fun selectsuccessbuy(username:String)
    {
        apiConnectSelectsuccessBuy.selectsuccessbuy().showSuccessbuy("selectsuccessbuy",username).enqueue(this)
    }

    override fun onResponse(call: Call<List<Buy>>, response: Response<List<Buy>>) {
        val data=response.body()
        if(data!=null)
        {
            sendSelectSuccessBuy.onsuccess(data)
        }
    }

    override fun onFailure(call: Call<List<Buy>>, t: Throwable) {
        sendSelectSuccessBuy.onerror(t)
    }

}