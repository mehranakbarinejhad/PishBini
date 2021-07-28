package ir.liyanamarket.predictlive.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.ListBuyActivity
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendGroupInterface
import ir.liyanamarket.predictlive.`interface`.SendSelectKalaInterface
import ir.liyanamarket.predictlive.`interface`.sendgroupselected
import ir.liyanamarket.predictlive.adapter.GroupKalaAdapter
import ir.liyanamarket.predictlive.adapter.KalaAdapter
import ir.liyanamarket.predictlive.dataclass.GroupKala
import ir.liyanamarket.predictlive.dataclass.Kala
import ir.liyanamarket.predictlive.db.FunctionDataBase
import ir.liyanamarket.predictlive.presenter.shop.group.PresenterApiConnectGroup
import ir.liyanamarket.predictlive.presenter.shop.kala.PresenterApiConnectSelectKala
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.activityshopcontent.*
import kotlinx.android.synthetic.main.toolbar_shop.*


import org.koin.android.ext.android.inject

import kotlin.collections.ArrayList

class ShopActivity : AppCompatActivity(), SendGroupInterface, SendSelectKalaInterface,sendgroupselected {


        //region Call Class With koin
    private val functionDataBase:FunctionDataBase by inject()
    private val presenterApiConnectSelectKala: PresenterApiConnectSelectKala by inject()
    private val presenterApiConnectGroup: PresenterApiConnectGroup by inject()
    private val groupKalaAdapter: GroupKalaAdapter by inject()
    private val kalaAdapter: KalaAdapter by inject()
    private val picasso: Picasso by inject()
    //endregion
        //region Public Variable
    private lateinit var groupactive:String
    private var arraylist = ArrayList<Kala>()
    lateinit var username: String
//endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        //regionset layout direction to RTl And set toolbar , drawer button
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_RTL
        setSupportActionBar(m_toolbar_shop)
        supportActionBar?.title = "فروشگاه"
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout_shop,
            m_toolbar_shop,
            R.string.open,
            R.string.close
        )
        drawer_layout_shop.addDrawerListener(toggle)
        toggle.syncState()
        //endregion
        //region set interface and adapter context activity and public variable value
        presenterApiConnectSelectKala.sendSelectKalaInterface = this
        presenterApiConnectGroup.sendGroupInterface = this
        groupKalaAdapter.sendgroupselected=this
        kalaAdapter.activity = this
        val usermodel=functionDataBase.selectuser()
       // username = intent.getStringExtra("username").toString()
    username=usermodel.username
    kalaAdapter.username = username
        groupactive="All"
        groupKalaAdapter.sort="new"
        //endregion
        //region navigation item clicked

        navigation_view_shop.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mnu_basket_nav -> {
                  closedrawer()
                gotoAnotherActivity(BasketActivity::class.java ,username)
                }
               R.id.mnu_mybuy_nav -> {
                   closedrawer()
                  gotoAnotherActivity(ListBuyActivity::class.java,username)
                } }
            true }
//endregion
        //region set profile image and name user to header navigation view
        val view = navigation_view_shop.getHeaderView(0)
        val txtNameHeadershop = view.findViewById<TextView>(R.id.txt_nameuser_shop_header)
        val imageprofileHeaderShop = view.findViewById<ImageView>(R.id.img_profileshop_header)

       // txtNameHeadershop.text = intent.getStringExtra("nameuser").toString()
   txtNameHeadershop.text = usermodel.name
      /*  picasso.load(intent.getStringExtra("imageprofile").toString()).fit().centerCrop()
            .into(imageprofileHeaderShop)*/
     picasso.load(usermodel.image).fit().centerCrop()
            .into(imageprofileHeaderShop)


        //endregion
        //region get group and kala
        presenterApiConnectGroup.getgroup()
        presenterApiConnectSelectKala.selectkala(groupactive,groupKalaAdapter.sort)

        //endregion
        //region btn_sort clicked(sort tab item)
       btn_jadidtarin.setOnClickListener {
           sortbuttonclicked(btn_jadidtarin,btn_arzantarin,btn_gerantarin,"new")
        }
      btn_arzantarin.setOnClickListener {
          sortbuttonclicked(btn_arzantarin,btn_jadidtarin,btn_gerantarin,"arzan")
        }
        btn_gerantarin.setOnClickListener {
            sortbuttonclicked(btn_gerantarin,btn_jadidtarin,btn_arzantarin,"geran")

        }
        //endregion




    }
        //region onsuccess method data from server
    override fun onsuccess(list: MutableList<Kala>) {
            try {
                arraylist = list as ArrayList<Kala>
                kalaAdapter.list = list
                recyclerkala.apply {
                    layoutManager =
                        GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
                    adapter = kalaAdapter
                }
            }catch (ex:Exception){}

    }
    override fun onsuccessgroup(list: List<GroupKala>) {
        try {
            groupKalaAdapter.list = list
            groupKalaAdapter.recyclerView = recyclerkala
            recycler_groupkala.apply {
                layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                adapter = groupKalaAdapter
            }
        }
        catch (ex:Exception){}
    }
    //endregion
        //region onerror method data from server
    override fun onerror(t: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()

    }
    override fun onerrorgroup(t: Throwable) {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
    }

    //endregion
        //region callback groupactive interface
    override fun groupactive(groupname: String) {
        groupactive=groupname
    }
    //endregion
        //region On back pressed Click
    override fun onBackPressed() {
        if(!closedrawer()) { finish() } }
    //endregion
        //region inflate toolbar menu and command item clicked
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.mnu_searchactivity -> {
             val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra("listf", arraylist)
                startActivity(intent)

            }
            R.id.mnu_show_basket -> {
                gotoAnotherActivity(BasketActivity::class.java,username)
            }


        }
        return super.onOptionsItemSelected(item)
    }
//endregion
        // region function

    //region fun close navigation drawer
        private fun closedrawer():Boolean{
            return if (drawer_layout_shop.isDrawerOpen(GravityCompat.START)) {
                drawer_layout_shop.closeDrawer(GravityCompat.START)
                true
            } else{
                false
            }
}
    //endregion

        //region fun go other activity
    private fun gotoAnotherActivity(ac:Class<*>, username:String)
    {
        val intent=Intent(this,ac)
        intent.putExtra("username", username)
        startActivity(intent)
    }
    //endregion

        //region fun sort buttons clicked
private fun sortbuttonclicked(activebutton: Button,disableButton1:Button,disableButton2:Button,sort:String)
{

        activebutton.isEnabled = false
        disableButton1.isEnabled=true
        disableButton2.isEnabled=true
        activebutton.setBackgroundResource(R.drawable.shaperecyclerkalaitempink)
        disableButton1.setBackgroundResource(R.drawable.shaperecyclerkalaitemgreen)
        disableButton2.setBackgroundResource(R.drawable.shaperecyclerkalaitemgreen)
    groupKalaAdapter.sort=sort
    presenterApiConnectSelectKala.selectkala(groupactive,groupKalaAdapter.sort)

}
    //endregion

    //endregion
}

