package ir.liyanamarket.predictlive.presenter.validatephonenumber

import ir.liyanamarket.predictlive.`interface`.SendValidatePhoneNumberinterface
import ir.liyanamarket.predictlive.dataclass.ValidatePhoneNumber
import ir.liyanamarket.predictlive.model.validatephonenumber.ApiConnectValidatePhoneNumber
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectValidatePhoneNumber:KoinComponent,Callback<List<ValidatePhoneNumber>> {
    private val apiConnectValidatePhoneNumber:ApiConnectValidatePhoneNumber by inject()
lateinit var sendValidatePhoneNumberinterface: SendValidatePhoneNumberinterface
    fun getresult(phonenumber:String){
        apiConnectValidatePhoneNumber.result().resultnumber("validatephonenumber",phonenumber).enqueue(this)

    }
    override fun onResponse(
        call: Call<List<ValidatePhoneNumber>>,
        response: Response<List<ValidatePhoneNumber>>
    ) {

        val data=response.body()
        if(data!=null)
        {
            sendValidatePhoneNumberinterface.onsuccess(data)
        }
    }

    override fun onFailure(call: Call<List<ValidatePhoneNumber>>, t: Throwable) {
    sendValidatePhoneNumberinterface.onerror(t)
    }

}