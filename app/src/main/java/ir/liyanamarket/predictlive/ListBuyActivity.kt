package ir.liyanamarket.predictlive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ir.liyanamarket.predictlive.`interface`.SendSelectSuccessBuy
import ir.liyanamarket.predictlive.adapter.SuccessBuyActivityAdapter
import ir.liyanamarket.predictlive.dataclass.Buy
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterSelectSuccessBuy
import kotlinx.android.synthetic.main.activity_list_buy.*
import org.koin.android.ext.android.inject

class ListBuyActivity : AppCompatActivity(),SendSelectSuccessBuy {
    private val presenterSelectSuccessBuy:PresenterSelectSuccessBuy by inject()
    private val successBuyActivityAdapter:SuccessBuyActivityAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_buy)
        val username=intent.getStringExtra("username").toString()
        Toast.makeText(this,username,Toast.LENGTH_LONG).show()
        presenterSelectSuccessBuy.sendSelectSuccessBuy=this
        presenterSelectSuccessBuy.selectsuccessbuy(username)
    }

    override fun onsuccess(list: List<Buy>) {
        successBuyActivityAdapter.list=list
        recycler_list_success1.apply {
            layoutManager=LinearLayoutManager(this@ListBuyActivity,LinearLayoutManager.VERTICAL,false)
            adapter=successBuyActivityAdapter
        }

    }

    override fun onerror(t: Throwable) {
        Toast.makeText(this,"خطایی رخ داده است دوباره تلاش کنید",Toast.LENGTH_LONG).show()

    }
}