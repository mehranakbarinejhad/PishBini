package ir.liyanamarket.predictlive.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendSelectBuy
import ir.liyanamarket.predictlive.dataclass.Buy
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterApiConnectSelectBuy
import kotlinx.android.synthetic.main.activity_buy_success.*
import org.koin.android.ext.android.inject

class BuySuccessActivity : AppCompatActivity(),SendSelectBuy {
    private val presenterApiConnectSelectBuy:PresenterApiConnectSelectBuy by inject()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR

        setContentView(R.layout.activity_buy_success)
        val codesefaresh= intent.getIntExtra("codesefaresh",0).toString()
        val namekharidar=intent.getStringExtra("name").toString()
        val datekharid=intent.getStringExtra("datekharid").toString()



        txt_code_peygiri.text= "کد پیگری:$codesefaresh"
        txt_namekharidar_successbuyactivity.text=namekharidar
        txt_date_successbuyactivity.text=datekharid
        presenterApiConnectSelectBuy.sendSelectBuy=this
        presenterApiConnectSelectBuy.selectbycode(codesefaresh)


    }

    override fun onsuccessbylist(list: List<Buy>) {
        Toast.makeText(this,list.toString(),Toast.LENGTH_LONG).show()
    }

    override fun onerrorbuylist(t: Throwable) {
        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
    }
}