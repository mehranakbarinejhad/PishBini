package ir.liyanamarket.predictlive.presenter.sendcode

import ir.liyanamarket.predictlive.`interface`.SendSmsCodeinterface
import ir.liyanamarket.predictlive.dataclass.SmsCode
import ir.liyanamarket.predictlive.model.sendcode.ApiConnectSendCode
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSendCode:KoinComponent,Callback<List<SmsCode>> {
    private val apiConnectSendCode:ApiConnectSendCode by inject()
    lateinit var sendSmsCodeinterface: SendSmsCodeinterface
    fun getcode(phonenumber:String) {
       apiConnectSendCode.sendcode().sendcode("sendcodesms", phonenumber).enqueue(this)
    }
    override fun onResponse(call: Call<List<SmsCode>>, response: Response<List<SmsCode>>) {
      val data=response.body()
    if(data!=null)
    {
                sendSmsCodeinterface.onsuccess(data)
    }
    }

    override fun onFailure(call: Call<List<SmsCode>>, t: Throwable) {
                sendSmsCodeinterface.onerror(t)
    }

}