package ir.liyanamarket.predictlive.presenter.user

import ir.liyanamarket.predictlive.`interface`.SendSelectRankingUser
import ir.liyanamarket.predictlive.dataclass.RankingUser
import ir.liyanamarket.predictlive.model.user.ApiConnectRankingUser
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectRankingUser:KoinComponent,Callback<List<RankingUser>> {
    private val apiConnectRankingUser:ApiConnectRankingUser by inject()
    lateinit var sendSelectRankingUser: SendSelectRankingUser
    fun selectuserranking(username:String){
        apiConnectRankingUser.getranking().selectranking("selectranking",username).enqueue(this)
    }

    override fun onResponse(call: Call<List<RankingUser>>, response: Response<List<RankingUser>>) {
        val data=response.body()
        if(data!=null)
        {
          sendSelectRankingUser.onsuccessrankinguser(data)
        }
    }

    override fun onFailure(call: Call<List<RankingUser>>, t: Throwable) {
    sendSelectRankingUser.onerrorrankinguser(t)
    }
}