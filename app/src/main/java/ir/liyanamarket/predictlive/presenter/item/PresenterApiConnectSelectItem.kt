package ir.liyanamarket.predictlive.presenter.item

import ir.liyanamarket.predictlive.`interface`.SendSelectItem
import ir.liyanamarket.predictlive.dataclass.Item
import ir.liyanamarket.predictlive.model.item.ApiConnectItem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSelectItem:KoinComponent,Callback<List<Item>> {
    private val apiconnectselectitem:ApiConnectItem by inject()
    lateinit var sendSelectItem: SendSelectItem
    fun selectitem(){
        apiconnectselectitem.selectitem().selectitem("selectitem").enqueue(this)
    }

    override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
        val data=response.body()
        if(data!=null)
        {
            sendSelectItem.onsuccessitem(data)
        }
    }

    override fun onFailure(call: Call<List<Item>>, t: Throwable) {
        sendSelectItem.onerroritem(t)
    }
}