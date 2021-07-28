package ir.liyanamarket.predictlive.presenter.user

import ir.liyanamarket.predictlive.`interface`.SendChangeprofileDetails
import ir.liyanamarket.predictlive.dataclass.ChangeprofileDetails
import ir.liyanamarket.predictlive.model.user.ApiConnectChangeprofileDetails
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectChangeProfileDetails:KoinComponent,Callback<ChangeprofileDetails> {
    private val apiConnectChangeprofileDetails:ApiConnectChangeprofileDetails by inject()
    lateinit var sendChangeprofileDetails: SendChangeprofileDetails

    fun changeprofisledetails(username:String,newusername:String,name:String,password:String,phonenumber:String,typechange:String)
    {
        apiConnectChangeprofileDetails.changedetails().changedetails("changedetailsprofile",username,newusername,name,password,phonenumber,typechange).enqueue(this)
    }

    override fun onResponse(
        call: Call<ChangeprofileDetails>,
        response: Response<ChangeprofileDetails>
    ) {
     val data=response.body()
        if(data!=null)
        {
            sendChangeprofileDetails.onsuccesschangeprofiledetails(data)
        }
    }

    override fun onFailure(call: Call<ChangeprofileDetails>, t: Throwable) {
        sendChangeprofileDetails.onerrorchangeprofiledetails(t)
    }
}