package ir.liyanamarket.predictlive.presenter.shop.basket

import ir.liyanamarket.predictlive.`interface`.SendDeleteBasket
import ir.liyanamarket.predictlive.dataclass.ShowBasket
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectDeleteBasket
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectDeleteBasket:Callback<List<ShowBasket>>,KoinComponent {
    lateinit var sendDeleteBasket: SendDeleteBasket
    private val apiConnectDeleteBasket:ApiConnectDeleteBasket by inject()
    fun deletebasket(username:String,codekala:String,sizekala:String){
        apiConnectDeleteBasket.deletebasket().deletebasket("deletebasket",username,codekala,sizekala).enqueue(this)
    }

    override fun onResponse(call: Call<List<ShowBasket>>, response: Response<List<ShowBasket>>) {
        val data=response.body()
        if(data!=null)
        {
            sendDeleteBasket.onsuccessDeleteBasket(data)
        }
    }

    override fun onFailure(call: Call<List<ShowBasket>>, t: Throwable) {
     sendDeleteBasket.onerrorDeleteBasket(t)
    }
}