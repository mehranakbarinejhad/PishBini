package ir.liyanamarket.predictlive.view

import android.annotation.SuppressLint
import android.content.Intent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendinsertBasket
import ir.liyanamarket.predictlive.adapter.SpinerSizeAdapter
import ir.liyanamarket.predictlive.dataclass.InsertBasketResult
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectInsertBasket
import kotlinx.android.synthetic.main.activity_details_kala.*
import org.koin.android.ext.android.inject
import java.text.DecimalFormat

class DetailsKalaActivity : AppCompatActivity(),SendinsertBasket {
    private val picasso: Picasso by inject()
    private val spinerSizeAdapter:SpinerSizeAdapter by inject()
    private val presenterApiConnectInsertBasket: PresenterApiConnectInsertBasket by inject()
lateinit var username:String
    private val list= mutableListOf("--")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_kala)
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR


                presenterApiConnectInsertBasket.sendinsertBasket=this
         username=intent.getStringExtra("username").toString()
        val codekala=intent.getStringExtra("codekala").toString()
        try {
            val descriptionkala = splitDescriptionWithKama(intent.getStringExtra("description").toString())
            val detailskala = splitDescriptionWithKama(intent.getStringExtra("details").toString())
            val sizekala=intent.getStringExtra("size").toString()
            splitsizewithkama(sizekala,list)
            spinerSizeAdapter.list=list
            spinersize.adapter=spinerSizeAdapter
            img_kala_details_activity.animation =
                AnimationUtils.loadAnimation(this, R.anim.zoomout)
            picasso.load(intent.getStringExtra("image").toString()).fit().into(img_kala_details_activity)
            txt_price_details_activity.text =DecimalFormat("###,###,###").format(intent.getStringExtra("price").toString().toLong())
            txt_titr_details_activity.text = intent.getStringExtra("titr").toString()
            txt_details_detailsactivity.movementMethod = ScrollingMovementMethod()
            txt_details_detailsactivity.text = descriptionkala


            //region create Tab Layout And Click Command
            val detailstab = tablayout_detailsactivity.newTab()
            detailstab.text = "مشخصات"
            tablayout_detailsactivity.addTab(detailstab)
            val descriptiontab = tablayout_detailsactivity.newTab()

            descriptiontab.text = "توضیحات"
            descriptiontab.view.setBackgroundResource(R.drawable.selecttabitemshape)
            tablayout_detailsactivity.addTab(descriptiontab)
            tablayout_detailsactivity.selectTab(descriptiontab)
            tablayout_detailsactivity.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        if (tab.position == 0) {
                            txt_details_detailsactivity.text = detailskala
                            detailstab.view.setBackgroundResource(R.drawable.selecttabitemshape)
                            descriptiontab.view.setBackgroundResource(R.drawable.shapetabitemunselected)
                        } else {
                            txt_details_detailsactivity.text = descriptionkala
                            descriptiontab.view.setBackgroundResource(R.drawable.selecttabitemshape)
                            detailstab.view.setBackgroundResource(R.drawable.shapetabitemunselected)


                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
            //endregion


        } catch (ex: Exception) {
        }




btn_addtobasket.setOnClickListener {
    val indexsize=spinersize.selectedItemPosition
    val sizekala=spinersize.getItemAtPosition(indexsize).toString()
    if(indexsize!=0)
    {
        presenterApiConnectInsertBasket.insertbasket(username,codekala,"1",sizekala)
    }
    else
    {
        Toast.makeText(this,"لطفا سایز مورد نظر را انتخاب کنید",Toast.LENGTH_LONG).show()
    }

}


    }


    override fun onBackPressed() {
        finish()
    }

    fun splitDescriptionWithKama(text: String): String {
        var result = ""
        val spltext = text.split(',')
        spltext.forEach {
            result += it + "\n\n"
        }
        return result
    }


    fun splitsizewithkama(text:String,list: MutableList<String>){

        val spltext = text.split(',')
        spltext.forEach {
           list.add(it)
        }

    }

    override fun onsuccessBasket(list: List<InsertBasketResult>) {
        if(list[0].result=="true")
        {
            Toast.makeText(this,"به لیست سبد خرید اضافه شد",Toast.LENGTH_LONG).show()
           val intent=Intent(applicationContext, BasketActivity::class.java)
            intent.putExtra("username",username)
            startActivity(intent)
                finish()
        }
        else{
            Toast.makeText(this,"این کالا در سسبد شما موجود می باشد می تواند از قسمت سبد خرید من تعداد آن را افزایش یا کاهش دهید",Toast.LENGTH_LONG).show()
        }

    }

    override fun onerrorbasket(t: Throwable) {
        Toast.makeText(this,"خطایی رخ داده است",Toast.LENGTH_LONG).show()
    }
}