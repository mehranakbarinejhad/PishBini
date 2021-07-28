package ir.liyanamarket.predictlive.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendUsersInterface
import ir.liyanamarket.predictlive.dataclass.Users
import ir.liyanamarket.predictlive.db.FunctionDataBase
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectUser
import ir.liyanamarket.predictlive.utils.MyMessage
import ir.liyanamarket.predictlive.utils.SaveLoginInfo
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject


class LoginActivity : AppCompatActivity(), SendUsersInterface {
private val functiondatabase:FunctionDataBase by inject()
    //region create class wth koin
    private val presenterApiConnectUser: PresenterApiConnectUser by inject()
  //  private val fragmentProgress: FragmentProgressBar by inject()
    private val myMessage:MyMessage by inject()
    private val saveLoginInfo: SaveLoginInfo by inject()
//endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        window.decorView.layoutDirection=View.LAYOUT_DIRECTION_LTR
        //region set interface and variable public
        presenterApiConnectUser.sendUsersInterface = this
        myMessage.activity=this
        //endregion
        //region check in remmember login info
        val logininfo = saveLoginInfo.load()
        val usernameshared = logininfo.first
        val passwordshared = logininfo.second
        if (usernameshared != "" && passwordshared != "") {
            edt_username.setText(usernameshared)
            edt_password.setText(passwordshared)
        } else {
            swch_remmemberme.isChecked = false
        }
        //endregion
        //region Login button clicked and check validate input text
        btn_login.setOnClickListener {
            btn_login.isEnabled=false

            btn_login.startAnimation(AnimationUtils.loadAnimation
                (this,R.anim.buttonanimation))
            btn_login.text="در حال ورود"
           btn_login.setBackgroundResource(R.drawable.loginbuttonischecked)
            if (edt_username.text.toString().isEmpty() || edt_password.text.toString().isEmpty()) {
                   myMessage.show("نام کاربری و رمز عبور را وارد نمایید.")
                btn_login.isEnabled=true
                btn_login.clearAnimation()
                return@setOnClickListener
            }
          // fragmentProgress.show(supportFragmentManager, "progressbar")
            presenterApiConnectUser.getusers(edt_username.text.toString())
        }
        //endregion
        //region Text Recovery Click
        txt_revovery.setOnClickListener {
           val intent = Intent(applicationContext, RecoveryActivity::class.java)
            startActivity(intent)
            finish()
        }
        //endregion
        //region text register click
            txt_createaccountclick.setOnClickListener {
                val intent=Intent(applicationContext,NumberActivity::class.java)
                startActivity(intent)
                finish()
            }
        //endregion
    }


    // region callback On Success Data
    override fun onsuccess(list: List<Users>) {


        if (list.count() != 0) {
            if (edt_password.text.toString() == list[0].password) {
                if (swch_remmemberme.isChecked) {
                    saveLoginInfo.save(edt_username.text.toString(), edt_password.text.toString())
                } else {
                    saveLoginInfo.save("", "")
                }
                functiondatabase.insertuser(edt_username.text.toString(),list[0].name,edt_password.text.toString(),list[0].image)
                val intentlogin = Intent(applicationContext, HomeActivity::class.java)
                putDataUserToIntent(list, intentlogin)
                startActivity(intentlogin)
                finish()


            } else {
                myMessage.show("رمز عبور اشتباه می باشد.")

            }
        } else {

            myMessage.show( "نام کاربری اشتباه می باشد.")

        }

        btn_login.isEnabled=true
        btn_login.clearAnimation()
        btn_login.text="ورود"
        btn_login.setBackgroundResource(R.drawable.loginbutton)



    }
    //endregion


    //region Callback On error data
    override fun onerror(t: Throwable) {
        btn_login.clearAnimation()
        btn_login.isEnabled=true
        btn_login.text="ورود"
        btn_login.setBackgroundResource(R.drawable.loginbutton)

        myMessage.show("لطفا وضعیت اینترنت را بررسی نمایید." )

    }
    //endregion


    //region Function Put user Data To Intent
    private fun putDataUserToIntent(list: List<Users>, intent: Intent) {
        intent.putExtra("usernameloginuser", edt_username.text.toString())
        intent.putExtra("nameuser", list[0].name)
        intent.putExtra("passwordloginuser", edt_password.text.toString())
        intent.putExtra("imageloginuser", list[0].image)
        intent.putExtra("phonenumberloginuser", list[0].phonenumber)
        intent.putExtra("scoreloginuser", list[0].Score)

    }




    //endregion
}