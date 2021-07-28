package ir.liyanamarket.predictlive.presenter.register

import ir.liyanamarket.predictlive.`interface`.SendRegisterinterface
import ir.liyanamarket.predictlive.dataclass.Register
import ir.liyanamarket.predictlive.model.register.ApiConnectRegister
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectRegister:KoinComponent,Callback<List<Register>> {
    private val apiConnectRegister:ApiConnectRegister by inject()
    lateinit var sendRegisterinterface: SendRegisterinterface

    fun register(name:String,username:String,password:String,phonenumber:String){
        apiConnectRegister.register().register("insertuser",name,username,password,phonenumber).enqueue(this)
    }

    override fun onResponse(call: Call<List<Register>>, response: Response<List<Register>>) {
        val data=response.body()
                if(data!=null)
                {
                    sendRegisterinterface.onsuccess(data)
                }

    }

    override fun onFailure(call: Call<List<Register>>, t: Throwable) {
                    sendRegisterinterface.onerror(t)
    }


}