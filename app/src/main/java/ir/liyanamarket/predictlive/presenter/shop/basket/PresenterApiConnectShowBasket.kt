package ir.liyanamarket.predictlive.presenter.shop.basket

import ir.liyanamarket.predictlive.`interface`.SendShowBasket
import ir.liyanamarket.predictlive.dataclass.ShowBasket
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectShowBasket
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectShowBasket:KoinComponent,Callback<List<ShowBasket>> {
    lateinit var sendShowBasket: SendShowBasket
    private val apiConnectShowBasket:ApiConnectShowBasket by inject()

    fun showbasket(username:String){
        apiConnectShowBasket.showbasket().showbasket("showallbasket",username).enqueue(this)
    }

    override fun onResponse(call: Call<List<ShowBasket>>, response: Response<List<ShowBasket>>) {
val data=response.body()
    if(data!=null)
    {

            sendShowBasket.onsuccessShowBasket(data)

    }
    }

    override fun onFailure(call: Call<List<ShowBasket>>, t: Throwable) {
sendShowBasket.onerrorShowBasket(t)
    }

}