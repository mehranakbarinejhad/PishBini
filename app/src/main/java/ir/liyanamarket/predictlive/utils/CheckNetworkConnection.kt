package ir.liyanamarket.predictlive.utils
import android.app.AlertDialog

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import ir.liyanamarket.predictlive.`interface`.SendGroupInterface
import ir.liyanamarket.predictlive.dataclass.GroupKala
import ir.liyanamarket.predictlive.fragment.FragmentProgressBar
import ir.liyanamarket.predictlive.presenter.shop.group.PresenterApiConnectGroup
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
@Suppress("DEPRECATION")
class CheckNetworkConnection(var context:Context):KoinComponent,SendGroupInterface {
    lateinit var activity:AppCompatActivity
private val presenterApiConnectGroup:PresenterApiConnectGroup by inject()
   // private val fragmentProgressBar=FragmentProgressBar()
private var resultInternet=false
    private var finalresut=false

    fun internetStatus():Boolean {

   //   fragmentProgressBar.show(activity.supportFragmentManager,"show")
        if(accessinternet())
        {
            finalresut=true
    //   fragmentProgressBar.dismiss()

        }
        else
        {
            finalresut=false
          // fragmentProgressBar.dismiss()
            val builder=AlertDialog.Builder(activity)
            builder.setTitle("خطا در اتصال به اینترنت")
            builder.setMessage("لطفا وضعیت اینترنت را بررسی نمایید")
                .setCancelable(false)
            builder.setPositiveButton("تلاش دوباره"){_,_ ->
                internetStatus()
            }
            builder.show()

        }
        return finalresut
    }


  private  fun accessinternet():Boolean{
        presenterApiConnectGroup.sendGroupInterface=this
        if(checknetwork())
        {

            presenterApiConnectGroup.getgroup()
        }
        else
        {
            resultInternet=false
        }
        return resultInternet
    }


   private fun checknetwork():Boolean{
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkinfo=connectivityManager.activeNetworkInfo
            if(networkinfo!=null && networkinfo.isConnected &&networkinfo.isAvailable)
            {
                return true
            }

        return false

    }

    override fun onsuccessgroup(list: List<GroupKala>) {
        resultInternet=true
    }

    override fun onerrorgroup(t: Throwable) {
        resultInternet=false

    }






}