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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_recovery.*
import org.koin.android.ext.android.inject

class RecoveryActivity : AppCompatActivity(), SendValidatePhoneNumberinterface {
    private val checkValidateInputPhoneNumber: CheckValidateInputPhoneNumber by inject()
    private val presenterApiConnectValidatePhoneNumber: PresenterApiConnectValidatePhoneNumber by inject()
    private val myMessage: MyMessage by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR

        myMessage.activity=this
        checkValidateInputPhoneNumber.activity=this
        presenterApiConnectValidatePhoneNumber.sendValidatePhoneNumberinterface=this
        btn_next_RecoveryActivity.setOnClickListener {
            btn_next_RecoveryActivity.isEnabled=false
            if(!btn_next_RecoveryActivity.isEnabled) {
                if (checkValidateInputPhoneNumber.validatenumber(edt_phonenumber_recoveryactivity.text.toString())) {
                    if(!btn_next_RecoveryActivity.isEnabled){
                    presenterApiConnectValidatePhoneNumber.getresult(
                        edt_phonenumber_recoveryactivity.text.toString()
                    )}
                } else {
                    btn_next_RecoveryActivity.isEnabled = true

                }
            }
        }

    }

    override fun onsuccess(list: List<ValidatePhoneNumber>) {
        btn_next_RecoveryActivity.isEnabled=true

        if(!list[0].result){
            myMessage.show("این شماره تلفن ثبت نام نکرده است")
        }
        else
        {
            val intent= Intent(applicationContext, ValidateCodeRecoveryActivity::class.java)
            intent.putExtra("phonenumber",edt_phonenumber_recoveryactivity.text.toString())
            startActivity(intent)
            finish()

        }

    }

    override fun onerror(t: Throwable) {
        try {
            btn_next_RecoveryActivity.isEnabled = true
            myMessage.show("وضعیت اینترنت خود را بررسی نمایید.")
        }
        catch (ex:Exception){}
    }

    override fun onBackPressed() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}