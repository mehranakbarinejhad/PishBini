package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendValidatePhoneNumberinterface
import ir.liyanamarket.predictlive.dataclass.ValidatePhoneNumber
import ir.liyanamarket.predictlive.presenter.validatephonenumber.PresenterApiConnectValidatePhoneNumber
import ir.liyanamarket.predictlive.utils.CheckValidateInputPhoneNumber
import ir.liyanamarket.predictlive.utils.MyMessage
import kotlinx.android.synthetic.main.activity_number.*
import org.koin.android.ext.android.inject

class NumberActivity : AppCompatActivity(),SendValidatePhoneNumberinterface {
    private val checkValidateInputPhoneNumber: CheckValidateInputPhoneNumber by inject()
    private val presenterApiConnectValidatePhoneNumber:PresenterApiConnectValidatePhoneNumber by inject()
    private val myMessage: MyMessage by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR

        setContentView(R.layout.activity_number)
        myMessage.activity=this
        checkValidateInputPhoneNumber.activity=this
        presenterApiConnectValidatePhoneNumber.sendValidatePhoneNumberinterface=this
        btn_next_numberactivity.setOnClickListener {
            btn_next_numberactivity.isEnabled=false
            btn_next_numberactivity.text="در حال انجام"
            btn_next_numberactivity.setBackgroundResource(R.drawable.loginbuttonischecked)

            if (checkValidateInputPhoneNumber.validatenumber(edt_phonenumber_numberactivity.text.toString())) {

                presenterApiConnectValidatePhoneNumber.getresult(edt_phonenumber_numberactivity.text.toString())
            }
            else
            {
                btn_next_numberactivity.isEnabled=true
                btn_next_numberactivity.text="تایید"
                btn_next_numberactivity.setBackgroundResource(R.drawable.loginbutton)
            }


        }

        txt_loginclick_numberactivity.setOnClickListener {
          gologinactivity()
        }
    }

    override fun onsuccess(list: List<ValidatePhoneNumber>) {
        btn_next_numberactivity.isEnabled=true
        btn_next_numberactivity.text="تایید"
        btn_next_numberactivity.setBackgroundResource(R.drawable.loginbutton)
        if(list[0].result){
            myMessage.show("این شماره تلفن قبلا ثبت نام کرده است")
        }
        else
        {
          val intent=Intent(applicationContext, ValidateCodeActivity::class.java)
            intent.putExtra("phonenumber",edt_phonenumber_numberactivity.text.toString())
            startActivity(intent)
            finish()

        }
    }

    override fun onerror(t: Throwable) {
        btn_next_numberactivity.isEnabled=true
        btn_next_numberactivity.text="تایید"
        btn_next_numberactivity.setBackgroundResource(R.drawable.loginbutton)
        myMessage.show("اتصال اینترنت را بررسی کنید ")

    }

    override fun onBackPressed() {
        gologinactivity()
    }


    private fun gologinactivity(){
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}