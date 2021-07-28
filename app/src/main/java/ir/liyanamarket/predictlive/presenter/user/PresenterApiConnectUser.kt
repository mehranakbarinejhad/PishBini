package ir.liyanamarket.predictlive.presenter.user




import androidx.appcompat.app.AppCompatActivity
import ir.liyanamarket.predictlive.`interface`.SendUsersInterface
import ir.liyanamarket.predictlive.dataclass.Users
import ir.liyanamarket.predictlive.model.user.ApiConnectUsers
import ir.liyanamarket.predictlive.utils.CheckNetworkConnection
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PresenterApiConnectUser():KoinComponent,Callback<List<Users>> {
  lateinit var sendUsersInterface:SendUsersInterface
  //lateinit var activity: AppCompatActivity
  //private val checkNetworkConnection:CheckNetworkConnection by inject()

private val apiconnectuser:ApiConnectUsers by inject()


    fun getusers(username:String){
      //  checkNetworkConnection.activity=activity
//if(checkNetworkConnection.internetStatus()) {
    apiconnectuser.getdata().getusers("selectuser", username).enqueue(this)
//}

}
    fun getusersbynumber(phonenumber:String){
    //    checkNetworkConnection.activity=activity
       // if(checkNetworkConnection.internetStatus()) {
            apiconnectuser.getdatabynumber()
                .getusersbynumber("selectuserbyphonenumber", phonenumber).enqueue(this)
       // }
}



    override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
        val data=response.body()
        if(data!=null)
        {
         sendUsersInterface.onsuccess(data)

        }


    }

    override fun onFailure(call: Call<List<Users>>, t: Throwable) {
   sendUsersInterface.onerror(t)


    }
}