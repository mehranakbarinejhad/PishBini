package ir.liyanamarket.predictlive.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendChangeProfileInterface
import ir.liyanamarket.predictlive.`interface`.SendChangeprofileDetails
import ir.liyanamarket.predictlive.dataclass.ChangeProfile
import ir.liyanamarket.predictlive.dataclass.ChangeprofileDetails
import ir.liyanamarket.predictlive.presenter.changeprofile.PresenterApiconnectChangeProfile
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectChangeProfileDetails
import ir.liyanamarket.predictlive.utils.EncodeAndDecodeImage
import ir.liyanamarket.predictlive.utils.MyMessage
import kotlinx.android.synthetic.main.settingfragment.*
import org.koin.android.ext.android.inject

@Suppress("DEPRECATION")
class FragmentSetting : Fragment(), SendChangeProfileInterface, SendChangeprofileDetails {
    private val presenterApiConnectChangeProfileDetails: PresenterApiConnectChangeProfileDetails by inject()
    private val fragmentHome:FragmentHome by inject()
    private val fragmentprogressBar:FragmentProgressBar by inject()
    private val myMessage:MyMessage by inject()
    private val picasso: Picasso by inject()
    lateinit var activity: AppCompatActivity
    private val presenterApiconnectChangeProfile: PresenterApiconnectChangeProfile by inject()
    private val encodeAndDecodeImage: EncodeAndDecodeImage by inject()
    lateinit var strimage: String
    lateinit var username: String
     lateinit var userimage: String
     lateinit var completename: String
    lateinit var phonenumber: String
    lateinit var password: String
    private lateinit var currenttext:String
    private lateinit var aftechangetext:String
     var active:Boolean=false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settingfragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        active=true
        setClassConstructor()
        setValuePublicVariable()
        setUserDataToViews()
        showFileDialogForChangeProfile()

        txt_completename_settingactivity.setOnFocusChangeListener { _, _ ->
            focustextlayout(layouttxtname, txt_completename_settingactivity,"name") }
        txt_username_settingactivity.setOnFocusChangeListener { _, _ ->
            focustextlayout(layouttxtusername, txt_username_settingactivity,"username")

        }
        txt_password_settingactivity.setOnFocusChangeListener {_, _ ->
            focustextlayout(layouttxtpassword,txt_password_settingactivity,"password")
        }
        txt_phonenumber_settingactivity.setOnFocusChangeListener { _, _->
            focustextlayout(layouttxtphonenumbr,txt_phonenumber_settingactivity,"phonenumber")
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 21 && resultCode == -1 && data != null) {
            val path = data.data
            strimage = encodeAndDecodeImage.encodeimage(activity, path!!)
            fragmentprogressBar.show(activity.supportFragmentManager,"changeprofile")
            presenterApiconnectChangeProfile.changeprofile(strimage, username)
        }
    }

    override fun onsuccess(list: List<ChangeProfile>) {
fragmentprogressBar.dismiss()
            picasso.load(list[0].imageurl).fit().centerCrop().into(img_user_settingactivity)
            userimage=list[0].imageurl
    }

    override fun onerror(t: Throwable) {
        fragmentprogressBar.dismiss()
     myMessage.show("لطفا وضعیت اینترنت را بررس نمایید.")
    }


    private fun focustextlayout(textInputLayout: TextInputLayout, editText: EditText,typechange:String) {


        if (editText.hasFocus()) {
            if(editText.text.isNotEmpty()) {
                aftechangetext = editText.text.toString()
            }

                textInputLayout.apply {

                endIconMode = TextInputLayout.END_ICON_CUSTOM
                setEndIconDrawable(R.drawable.ic_baseline_done_24)
                setEndIconOnClickListener {
                    if (editText.text.isNotEmpty()) {
                        currenttext = editText.text.toString()
                        fragmentprogressBar.show(activity.supportFragmentManager,typechange)
                        presenterApiConnectChangeProfileDetails.changeprofisledetails(
                            username,
                            editText.text.toString(),
                            editText.text.toString(),
                            editText.text.toString(),
                            editText.text.toString(),
                            typechange
                        )
                    } else {
                        when (typechange) {
                            "username" -> {
                                myMessage.show("لطفا نام کاربری جدید را وارد کنید ")
                            }
                            "name" -> {
                                myMessage.show("لطفا نام و نام خانوادگی جدید را وارد کنید ")
                            }
                            "password" -> {
                                myMessage.show("لطفا نام پسورد جدید را وارد کنید ")
                            }
                            "phonenumber" -> {
                                myMessage.show("لطفا نام شماره موبایل جدید را وارد کنید ")
                            }
                        }
                    }
                }
            }

        } else {
            if (currenttext == editText.text.toString()) {
                editText.setText(currenttext)

            } else {
                editText.setText(aftechangetext)
            }

            textInputLayout.apply {
                endIconMode = TextInputLayout.END_ICON_NONE
                println(completename)
            }
        }
    }

    override fun onsuccesschangeprofiledetails(result: ChangeprofileDetails) {

                fragmentprogressBar.dismiss()
        when {
            result.username!="null"  -> {
                if(result.username!="validate") {
                    username = result.username
                    Toast.makeText(activity, "نام کاربری تغییر یافت.", Toast.LENGTH_LONG).show()
                }
                else {
                    txt_username_settingactivity.setText(username)
                    myMessage.show("این نام کاربری آزاد نمی باشد لطفا نام کاربری دیگری را وارد نمایید.")
                }
            }
            result.name!="null" -> {
                completename=result.name
                Toast.makeText(activity,"نام و نام خانوادگی تغییر یافت.",Toast.LENGTH_LONG).show()
            }
            result.phonenumber!="null" -> {
                if(result.phonenumber!="validate") {
                    phonenumber = result.phonenumber
                    Toast.makeText(activity, "شماره موبایل شما تغییر یافت.", Toast.LENGTH_LONG).show()
                }
                else{
                    myMessage.show("این شماره تلفن فبلا ثبت نام کرده است لطفا شماره تلفن دیگری را وارد نمایید.")
                        txt_phonenumber_settingactivity.setText(phonenumber)
                }
            }
            result.password!="" -> {
                password=result.password
                Toast.makeText(activity,"رمز عبور شما تغییر یافت.",Toast.LENGTH_LONG).show()
            }
        }
}

    override fun onerrorchangeprofiledetails(t: Throwable) {
        fragmentprogressBar.dismiss()
        myMessage.show("لطفا وضعیت اینترنت را بررسی نمایید.")
    }







    //regionFun Set Class Constructor When Called By koin
    private fun setClassConstructor(){
        fragmentHome.activity2=activity
        myMessage.activity=activity
        presenterApiconnectChangeProfile.sendChangeProfileInterface = this
        presenterApiConnectChangeProfileDetails.sendChangeprofileDetails = this
    }
    //endregion
    //regionFun Set Value Public Variable
    private fun setValuePublicVariable(){
        currenttext="null"
        aftechangetext=""
        completename = fragmentHome.completeusername
        username=fragmentHome.username
        phonenumber = fragmentHome.phonenumber
        userimage = fragmentHome.imageuser
        password = activity.intent.getStringExtra("passwordloginuser").toString()
    }
    //endregion
    //regionFun Set User Data To Views
    private fun setUserDataToViews()
    {
        txt_completename_settingactivity.setText(completename)
        txt_username_settingactivity.setText(username)
        txt_phonenumber_settingactivity.setText(phonenumber)
        txt_password_settingactivity.setText(password)
        picasso.load(userimage).fit().centerCrop().into(img_user_settingactivity)
    }
    //endregion
    //regionFun Open File Dialog And Select Picture For Change Profile
    private fun showFileDialogForChangeProfile()
    {
        btn_changeprofile.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 21)
        }
    }

    //endregion
}
