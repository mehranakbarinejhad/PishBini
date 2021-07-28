package ir.liyanamarket.predictlive.presenter.changeprofile

import ir.liyanamarket.predictlive.`interface`.SendChangeProfileInterface
import ir.liyanamarket.predictlive.dataclass.ChangeProfile
import ir.liyanamarket.predictlive.model.changeprofile.ApiConnectToChangeProfile
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiconnectChangeProfile:KoinComponent,Callback<List<ChangeProfile>> {
    lateinit var sendChangeProfileInterface: SendChangeProfileInterface
    private val apiconnectChangeProfile:ApiConnectToChangeProfile by inject()
    fun changeprofile(strimage:String,username:String){
        apiconnectChangeProfile.change().change("changeprofile",strimage,username).enqueue(this)
    }

    override fun onResponse(
        call: Call<List<ChangeProfile>>,
        response: Response<List<ChangeProfile>>
    ) {
        val data=response.body()
        if(data!=null)
        {
            sendChangeProfileInterface.onsuccess(data)
        }
    }

    override fun onFailure(call: Call<List<ChangeProfile>>, t: Throwable) {
                sendChangeProfileInterface.onerror(t)
    }
}