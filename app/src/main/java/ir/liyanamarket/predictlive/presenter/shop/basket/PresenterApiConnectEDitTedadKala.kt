package ir.liyanamarket.predictlive.presenter.shop.basket

import ir.liyanamarket.predictlive.`interface`.SendEditTedadKala
import ir.liyanamarket.predictlive.dataclass.ShowBasket
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectEditTedadKala
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectEDitTedadKala:Callback<List<ShowBasket>>,KoinComponent {
    lateinit var sendEditTedadKala: SendEditTedadKala
    private val apiConnectEditTedadKala:ApiConnectEditTedadKala by inject()
    fun edittedadkala(username:String,codekala:String,sizekala:String,tedadkala:String){
        apiConnectEditTedadKala.edittedadakala().edittedadkala("editbasket",username,codekala,sizekala,tedadkala).enqueue(this)

    }

    override fun onResponse(call: Call<List<ShowBasket>>, response: Response<List<ShowBasket>>) {
        val data=response.body()
        if(data!=null)
        {
            sendEditTedadKala.onsuccessEditTedadKala(data)
        }
    }

    override fun onFailure(call: Call<List<ShowBasket>>, t: Throwable) {
        sendEditTedadKala.onerrorEditTedadKala(t)
    }
}