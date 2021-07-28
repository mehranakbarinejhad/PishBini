package ir.liyanamarket.predictlive.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendUsersInterface
import ir.liyanamarket.predictlive.adapter.RankingAdapter
import ir.liyanamarket.predictlive.dataclass.Users
import ir.liyanamarket.predictlive.presenter.user.PresenterApiConnectUser
import kotlinx.android.synthetic.main.rankingfragment.*
import org.koin.android.ext.android.inject

@Suppress("DEPRECATION")
class FragmentRanking(private val thiscontext: Context):Fragment(),SendUsersInterface {
    private val picasso: Picasso by inject()
    lateinit var activity: AppCompatActivity
    private val fragmentProgress:FragmentProgressBar by inject()
  private val rankingAdapter:RankingAdapter by inject()
private val presenterApiConnectUser: PresenterApiConnectUser by inject()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.rankingfragment,container,false)


    }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    txt_message_rankingfragment.isSelected=true
      presenterApiConnectUser.sendUsersInterface=this
      fragmentProgress.show(activity.supportFragmentManager,"progressbar")
        presenterApiConnectUser.getusers("")




    }



    override fun onsuccess(list: List<Users>) {

        fragmentProgress.dismiss()
        try{

            picasso.load(list[0].image).fit().centerCrop().into(img_oneranking)
            txt_nameoneranking.text=list[0].name
            txt_scoreoneranking.text=list[0].Score.toString()
            picasso.load(list[1].image).fit().centerCrop().into(img2ranking)
            txt_nametworanking.text=list[1].name
            txt_scoretworanking.text=list[1].Score.toString()
            picasso.load(list[2].image).fit().centerCrop().into(img_treeranking)
            txt_namethreeranking.text=list[2].name
            txt_scorethreeranking.text=list[2].Score.toString()
            val list2= list as MutableList
            list2.removeAt(0)
            list2.removeAt(0)
            list2.removeAt(0)
            rankingAdapter.list=list2
            recycler_ranking.apply {
            layoutManager=LinearLayoutManager(thiscontext,LinearLayoutManager.VERTICAL,false)
              adapter=rankingAdapter
            }
        }
        catch (ex:Exception){}


    }

    override fun onerror(t: Throwable) {
        fragmentProgress.dismiss()
        showInternetConnectDialog()
       // Toast.makeText(thiscontext,"Error",Toast.LENGTH_LONG).show()

    }





    private fun showInternetConnectDialog(){
        val builder= AlertDialog.Builder(activity)
        builder.setTitle("خطا در اتصال به اینترنت")
        builder.setMessage("لطفا وضعیت اینترنت را بررسی نمایید")
            .setCancelable(false)
        builder.setPositiveButton("تلاش دوباره"){_,_ ->
            fragmentProgress.show(activity.supportFragmentManager,"progressbar")
            presenterApiConnectUser.getusers("")
        }
        builder.setNegativeButton("خروج از برنامه"){_,_->
            activity.finishAffinity()
        }
        builder.show()
    }




}