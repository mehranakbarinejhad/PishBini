package ir.liyanamarket.predictlive.presenter.shop.basket

import ir.liyanamarket.predictlive.`interface`.SendinsertBasket
import ir.liyanamarket.predictlive.dataclass.InsertBasketResult
import ir.liyanamarket.predictlive.model.shop.basket.ApiConnectInsertBasket
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectInsertBasket:Callback<List<InsertBasketResult>>,KoinComponent {
    lateinit var sendinsertBasket: SendinsertBasket
    private val apiConnectInsertBasket:ApiConnectInsertBasket by inject()

    fun insertbasket(username:String,codekala:String,tedadkala:String="1",sizekala:String=""){
        apiConnectInsertBasket.insertbasket().insertbasket("insertkalatobasket",username,codekala,tedadkala,sizekala).enqueue(this)
    }

    override fun onResponse(
        call: Call<List<InsertBasketResult>>,
        response: Response<List<InsertBasketResult>>
    ) {
val data=response.body()
    if(data!=null)
    {
        sendinsertBasket.onsuccessBasket(data)
    }

    }

    override fun onFailure(call: Call<List<InsertBasketResult>>, t: Throwable) {
        sendinsertBasket.onerrorbasket(t)
    }
}