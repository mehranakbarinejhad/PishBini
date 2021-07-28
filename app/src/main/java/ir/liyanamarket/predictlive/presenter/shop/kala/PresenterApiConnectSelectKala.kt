package ir.liyanamarket.predictlive.presenter.shop.kala

import android.content.Context
import android.widget.Toast
import ir.liyanamarket.predictlive.`interface`.SendSelectKalaInterface
import ir.liyanamarket.predictlive.dataclass.Kala
import ir.liyanamarket.predictlive.model.shop.kala.ApiConnectToSelectKala
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSelectKala(val context: Context) : KoinComponent,
    Callback<MutableList<Kala>> {
    private val apiConnectToSelectKala: ApiConnectToSelectKala by inject()
    lateinit var sendSelectKalaInterface: SendSelectKalaInterface
    fun selectkala(group: String,sort:String) {
       apiConnectToSelectKala.select().selectKala("selectkalawithgroup", group,sort).enqueue(this)
    }

    override fun onResponse(call: Call<MutableList<Kala>>, response: Response<MutableList<Kala>>) {

        val data = response.body()
        if (data != null) {
            sendSelectKalaInterface.onsuccess(data)
        }



    }

    override fun onFailure(call: Call<MutableList<Kala>>, t: Throwable) {
        sendSelectKalaInterface.onerror(t)
    }
}