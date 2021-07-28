package ir.liyanamarket.predictlive.presenter.shop.buy


import ir.liyanamarket.predictlive.`interface`.SendSelectBuy
import ir.liyanamarket.predictlive.dataclass.Buy
import ir.liyanamarket.predictlive.model.shop.buy.ApiConnectSelectBuy
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PresenterApiConnectSelectBuy : KoinComponent, Callback<List<Buy>> {
    private val apiConnectSelectBuy: ApiConnectSelectBuy by inject()
    lateinit var sendSelectBuy: SendSelectBuy

    fun selectbycode(code: String) {
        apiConnectSelectBuy.selectbucode().selectbycode("selectbuybycode", code).enqueue(this)
    }

    fun selectbyusername(username: String) {
        apiConnectSelectBuy.selectbucode().selectbycode("selectbuybyusername", username)
            .enqueue(this)
    }

    override fun onResponse(call: Call<List<Buy>>, response: Response<List<Buy>>) {
        val data = response.body()
        if (data != null) {
            sendSelectBuy.onsuccessbylist(data)
        }

    }

    override fun onFailure(call: Call<List<Buy>>, t: Throwable) {
        sendSelectBuy.onerrorbuylist(t)
    }
}