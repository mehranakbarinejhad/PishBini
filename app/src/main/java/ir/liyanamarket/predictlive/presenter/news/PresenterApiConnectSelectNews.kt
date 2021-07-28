package ir.liyanamarket.predictlive.presenter.news

import ir.liyanamarket.predictlive.`interface`.SendSelectNews
import ir.liyanamarket.predictlive.dataclass.News
import ir.liyanamarket.predictlive.model.news.ApiConnectSelectNews
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterApiConnectSelectNews:KoinComponent,Callback<List<News>> {
    private val apiConnectSelectNews:ApiConnectSelectNews by inject()
lateinit var sendSelectNews: SendSelectNews
    fun selectnews(){
        apiConnectSelectNews.selectNews().selectnews("selectnews").enqueue(this)
    }

    override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
        val data=response.body()
        if(data!=null)
        {
            sendSelectNews.onsuccessSelectNews(data)
        }
    }

    override fun onFailure(call: Call<List<News>>, t: Throwable) {
      sendSelectNews.onerrorSelectNews(t)
    }
}