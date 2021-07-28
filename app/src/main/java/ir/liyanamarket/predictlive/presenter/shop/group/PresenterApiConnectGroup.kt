package ir.liyanamarket.predictlive.presenter.shop.group

import ir.liyanamarket.predictlive.`interface`.SendGroupInterface
import ir.liyanamarket.predictlive.dataclass.GroupKala
import ir.liyanamarket.predictlive.model.shop.group.ApiConnectGroup
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PresenterApiConnectGroup:KoinComponent,Callback<List<GroupKala>> {
    private val apiConnectGroup:ApiConnectGroup by inject()
    lateinit var sendGroupInterface: SendGroupInterface
    fun getgroup()
    {
        apiConnectGroup.getgroup().getgroup().enqueue(this)
    }

    override fun onResponse(call: Call<List<GroupKala>>, response: Response<List<GroupKala>>) {
        val data=response.body()
        if(data!=null)
        {
            sendGroupInterface.onsuccessgroup(data)
        }
    }

    override fun onFailure(call: Call<List<GroupKala>>, t: Throwable) {
        sendGroupInterface.onerrorgroup(t)
    }


}