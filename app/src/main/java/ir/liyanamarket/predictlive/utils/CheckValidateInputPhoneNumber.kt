package ir.liyanamarket.predictlive.utils
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CheckValidateInputPhoneNumber:KoinComponent {
    lateinit var activity: AppCompatActivity
    private val myMessage: MyMessage by inject()
    fun validatenumber(phonenumber: String): Boolean {
        myMessage.activity=activity
        var bool = true
        when {
            phonenumber.isEmpty() -> {

               myMessage.show("لطفا شماره تلفن را وارد نمایید.")
                bool = false

            }
            phonenumber.startsWith('0') -> {

                myMessage.show("لطفا شماره تلفن را بدون عدد 0 وارد نمایید.")

                bool = false
            }
            phonenumber.length != 10 -> {

                myMessage.show("شماره تلفن بدون عدد 0 باید ده رقمی باشد")
                bool = false
            }

        }
        return bool

    }
}