package ir.liyanamarket.predictlive.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendSmsCodeinterface
import ir.liyanamarket.predictlive.dataclass.SmsCode
import ir.liyanamarket.predictlive.presenter.sendcode.PresenterApiConnectSendCode
import ir.liyanamarket.predictlive.utils.CheckNetworkConnection
import ir.liyanamarket.predictlive.utils.MyMessage
import kotlinx.android.synthetic.main.activity_validate_code_recovery.*
import org.koin.android.ext.android.inject

class ValidateCodeRecoveryActivity : AppCompatActivity(), SendSmsCodeinterface {
    private val presenterApiConnectSendCode: PresenterApiConnectSendCode by inject()
    private lateinit var smscode:String
    lateinit var phonenumber:String
    private val myMessage: MyMessage by inject()
    private val checkNetworkConnection:CheckNetworkConnection by inject()
    //regioncounddowntimer
private lateinit var countDownTimer:CountDownTimer
    val start=120000L
    var timer=start
    //endregion
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_code_recovery)
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR
checkNetworkConnection.activity=this
        myMessage.activity=this
        phonenumber=intent.getStringExtra("phonenumber").toString()
        txt_entercodetext_recoveryactivity.text=" ما یک کد به شماره  $phonenumber ,ارسال کردیم!لطفا آن را وارد کنید . "

        presenterApiConnectSendCode.sendSmsCodeinterface=this
        presenterApiConnectSendCode.getcode(phonenumber)


        txt_resendcodeclick_recoveryactivity.setOnClickListener {
            presenterApiConnectSendCode.sendSmsCodeinterface=this
            presenterApiConnectSendCode.getcode(phonenumber)
            Toast.makeText(this,"کد دوباره ارسال شد لطفا تا دریافت کد منتظر بمانید.",Toast.LENGTH_LONG).show()

            countDownTimer=object :CountDownTimer(start,1000L){
                override fun onTick(p0: Long) {
                    timer=p0
                    val min:Long=((timer/1000)/60)%60
                    val second=(timer/1000)%60
                    val timeFormat:String= String.format("%02d:%02d",min,second)
                    txt_resendcodeclick_recoveryactivity.text=timeFormat
                    txt_resendcodeclick_recoveryactivity.isEnabled=false


                }

                override fun onFinish() {
                  timer=0
                    txt_resendcodeclick_recoveryactivity.text="ارسال دوباره"
                    txt_resendcodeclick_recoveryactivity.isEnabled=true

                }

            }.start()




        }
        btn_next_code_recoveryactivity.setOnClickListener {
            val enteringtext=textCode(edt_numberone_recoveryactivity,edt_numbertwo_recoveryactivity,edt_numberthree_recoveryactivity,edt_numberfor_recoveryactivity,edt_numberfive_recoveryactivity)
            if(enteringtext==smscode) {
                if(checkNetworkConnection.internetStatus()) {
                    val intent = Intent(this, ResultRecoveryActivity::class.java)
                    intent.putExtra("phonenumber", phonenumber)
                    startActivity(intent)
                    finish()
                }



            }
            else
            {
                myMessage.show("کد وارد شده اشتباه می باشد.")
            }
        }

        //region set focus edittext
        edt_numberone_recoveryactivity.addTextChangedListener { setfocus(edt_numberone_recoveryactivity,edt_numbertwo_recoveryactivity) }
        edt_numbertwo_recoveryactivity.addTextChangedListener { setfocus(edt_numbertwo_recoveryactivity,edt_numberthree_recoveryactivity) }
        edt_numberthree_recoveryactivity.addTextChangedListener { setfocus(edt_numberthree_recoveryactivity,edt_numberfor_recoveryactivity) }
        edt_numberfor_recoveryactivity.addTextChangedListener { setfocus(edt_numberfor_recoveryactivity,edt_numberfive_recoveryactivity) }
    //endregion
    }

    override fun onsuccess(list: List<SmsCode>) {
        smscode = list[0].code
        phonenumber = list[0].phonenumber

    }

    override fun onerror(t: Throwable) {
        myMessage.show("لطفا وضعیت اینترنت خود را بررسی نمایید.")

    }
    private fun textCode(num1: EditText, num2: EditText, num3: EditText, num4: EditText, num5: EditText):String{
        return num1.text.toString()+num2.text.toString()+num3.text.toString()+num4.text.toString()+num5.text.toString()

    }
    private fun setfocus(firstedttext: EditText, lastedttext: EditText) {
        if (firstedttext.text.toString().length == 1) {
            lastedttext.requestFocus()

        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}