package ir.liyanamarket.predictlive.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendLastCodeSefaresh
import ir.liyanamarket.predictlive.`interface`.SendShowBasket
import ir.liyanamarket.predictlive.`interface`.SendinsertBuy
import ir.liyanamarket.predictlive.dataclass.Buy
import ir.liyanamarket.predictlive.dataclass.ResultCodeSefaresh
import ir.liyanamarket.predictlive.dataclass.ShowBasket
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectShowBasket
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterApiConnectLastCodeSefaresh
import ir.liyanamarket.predictlive.presenter.shop.buy.PresenterApiConnectinsertBuy
import ir.liyanamarket.predictlive.utils.MyMessage
import kotlinx.android.synthetic.main.activity_buy.*
import org.koin.android.ext.android.inject
import java.lang.Exception
import java.text.DecimalFormat

class BuyActivity : AppCompatActivity(), SendShowBasket, SendLastCodeSefaresh, SendinsertBuy {


    //region create class with koin
    private val presenterApiConnectShowBasket: PresenterApiConnectShowBasket by inject()
    private val presenterApiConnectLastCodeSefaresh: PresenterApiConnectLastCodeSefaresh by inject()
    private val presenterApiConnectinsertBuy: PresenterApiConnectinsertBuy by inject()
    private val myMessage: MyMessage by inject()

    //endregion
    //region Public variable
    private var lastcodesefaresh = 0
    lateinit var username: String
    lateinit var list: List<ShowBasket>

    //endregion
    //region oncreate method

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR

        //region Get Username From Intent And Set To Public Variable Username
        username = intent.getStringExtra("username").toString()
        //endregion
        //region set interface And Context
        presenterApiConnectLastCodeSefaresh.sendLastCodeSefaresh = this
        presenterApiConnectinsertBuy.sendinsertBuy = this
        presenterApiConnectShowBasket.sendShowBasket = this
        myMessage.activity = this
        //endregion
        //region Call Action Select Last Code Sefaresh
        presenterApiConnectLastCodeSefaresh.selectlast()
        //endregion
        //region Call Action Select Kala From Basket By Username
        presenterApiConnectShowBasket.showbasket(username)
        //endregion
        //region btn_buy commands action
        btn_payment.setOnClickListener {

            if (validateinputtext(
                    edt_namekharidar,
                    edt_phonenumber_buyactivity,
                    edt_ostan,
                    edt_shahrestan,
                    edt_completeaddress
                )
            ) {
                 var countlist=list.size-1
                while(countlist>=0)
                {
                    presenterApiConnectinsertBuy.insertbuy(
                        username,
                        lastcodesefaresh.toString(),
                        list[countlist].codekala,
                        list[countlist].kala[0].name,
                        list[countlist].kala[0].price,
                        list[countlist].tedadkala,
                        list[countlist].sizekala,
                        edt_namekharidar.text.toString(),
                        edt_phonenumber_buyactivity.text.toString(),
                        edt_ostan.text.toString() + " " + edt_shahrestan.text.toString() + " " + edt_completeaddress.text.toString()
                    )
                    countlist--

                }

            }
        }

        //endregion

    }
//endregion
    //region on success response presenter class from server
    override fun onsuccessShowBasket(list: List<ShowBasket>) {
        try {
            this.list = list
            val sumprice = fnSumPrice(list)
            txt_sumPrice_buyactivity.text = DecimalFormat("###,###,###").format(sumprice)
        } catch (ex: Exception) {
        }
    }
    override fun onsuccesslastcode(list: List<ResultCodeSefaresh>) {
        try {
            val last = list[0].codesefaresh.toInt()
            lastcodesefaresh = last + 1
        } catch (ex: Exception) {
        }
    }
    override fun onsuucessinsertbuy(list: List<Buy>) {
        try {
           // if(list.size==this.list.size) {
               val listbuy=list.size
            val listbasket=this.list.size
            if(listbuy==listbasket){
                this.list= emptyList()
                val intent= Intent(applicationContext, BuySuccessActivity::class.java)
                intent.putExtra("username",username)
                intent.putExtra("name",list[0].namekharidar)
                intent.putExtra("datekharid",list[0].datekharid)
                intent.putExtra("codesefaresh",lastcodesefaresh)
                startActivity(intent)
                finish()
            }

        } catch (ex: Exception) {
        }

    }
    //endregion
    //region onerror response presenterclass from server
    override fun onerrorShowBasket(t: Throwable) {
        try {
            showdialoginternet()

        } catch (ex: Exception) {
        }
    }
    override fun onerrorlastcode(t: Throwable) {
        try {
            showdialoginternet()

        } catch (ex: Exception) {
        }
    }
    override fun onerrorinsertbuy(t: Throwable) {

        myMessage.show("لطفا وضعیت اینترنت را بررسی کنید.")

    }
    //endregion
    //region Function
    //region function show dialog when no internet
    private fun showdialoginternet() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("خطا در اتصال به اینترنت")
        builder.setMessage("لطفا وضعیت اینترنت را بررسی کنید")
        builder.setPositiveButton("تلاش دوباره") { _, _ ->
            recreate()
        }
        builder.show()

    }
    //endregion
    //region function check validate input when input by user
    private fun validateinputtext(
        edtname: EditText,
        edtphonenumber: EditText,
        edtostan: EditText,
        edtshahrestan: EditText,
        edtaddresscomplete: EditText
    ): Boolean {

        when {
            edtname.text.isEmpty() -> {

                myMessage.show("لطفا نام و نام خانوادگی را وارد نمایید.")
                return false
            }
            edtphonenumber.text.isEmpty() -> {

                myMessage.show("لطفا شماره تلفن را وارد نمایید.")
                return false
            }
            edtostan.text.isEmpty() -> {

                myMessage.show("لطفا نام استان خود را وارد نمایید.")
                return false
            }
            edtshahrestan.text.isEmpty() -> {

                myMessage.show("لطفا نام شرستان حود را وارد نمایید.")
                return false
            }
            edtaddresscomplete.text.isEmpty() -> {

                myMessage.show("لطفاآدرس کامل خود  را وارد نمایید.")
                return false
            }
            else -> {

                return true
            }
        }
    }
    //endregion
    //region function sum price and return sumpric as long
    private fun fnSumPrice(list: List<ShowBasket>): Long {
        var sum = 0L
        var rowindex = list.size - 1
        while (rowindex >= 0) {
            sum += ((list[rowindex].kala[0].price.toLong()) * (list[rowindex].tedadkala.toLong()))
            rowindex--
        }
        return sum
    }
    //endregion
    //endregion
}
