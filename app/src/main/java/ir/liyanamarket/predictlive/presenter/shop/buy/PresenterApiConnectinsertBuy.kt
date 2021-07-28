package ir.liyanamarket.predictlive.presenter.shop.buy

import ir.liyanamarket.predictlive.`interface`.SendinsertBuy
import ir.liyanamarket.predictlive.dataclass.Buy
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectInsertBuy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectinsertBuy : Callback<List<Buy>>, KoinComponent {
    private val apiConnectinsertBuy: ApiConnectInsertBuy by inject()
    lateinit var sendinsertBuy: SendinsertBuy

    fun insertbuy(
        username: String,
        codesefaresh: String,
        codekala: String,
        namekala: String,
        pricekala: String,
        tedadkala: String,
        sizekala:String,
        namekharidar:String,
        phonenumber:String,
        address:String
    ) {
        apiConnectinsertBuy.inserttobuy().inserttoby(
            "inserttobuy",
            username,
            codesefaresh,
            codekala,
            namekala,
            pricekala,
            tedadkala,
            sizekala,
            namekharidar,
            phonenumber
        ,address
        ).enqueue(this)
    }

    override fun onResponse(call: Call<List<Buy>>, response: Response<List<Buy>>) {
        val data=response.body()
        if(data!=null)
        {
            sendinsertBuy.onsuucessinsertbuy(data)
        }
    }

    override fun onFailure(call: Call<List<Buy>>, t: Throwable) {
        sendinsertBuy.onerrorinsertbuy(t)
    }
}