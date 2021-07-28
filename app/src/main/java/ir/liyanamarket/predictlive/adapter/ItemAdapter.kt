package ir.liyanamarket.predictlive.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.*
import ir.liyanamarket.predictlive.dataclass.Item
import ir.liyanamarket.predictlive.fragment.FragmentRanking
import ir.liyanamarket.predictlive.fragment.FragmentSetting
import ir.liyanamarket.predictlive.view.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ItemAdapter:RecyclerView.Adapter<ItemAdapter.CustomHolderItem>(),KoinComponent {
    private val fragmentRanking:FragmentRanking by inject()
    private val fragmentSetting:FragmentSetting by inject()
    private val picasso:Picasso by inject()
    lateinit var list:List<Item>
    lateinit var activity:AppCompatActivity
    lateinit var username:String
    lateinit var completenameuser:String
    lateinit var imageuser:String
    lateinit var bottomNavigationView: BottomNavigationView
    inner class CustomHolderItem(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val itemname:TextView=itemview.findViewById(R.id.txt_itemname_homeactivity)
        val itemimage:ImageView=itemview.findViewById(R.id.img_item_homeactivity)
        val parentitem:RelativeLayout=itemview.findViewById(R.id.parent_item_homeactivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolderItem {
val view=LayoutInflater.from(parent.context).inflate(R.layout.itemhomeactivityrecycler,parent,false)

    return CustomHolderItem((view))}

    override fun onBindViewHolder(holder: CustomHolderItem, position: Int) {
        holder.itemname.text=list[position].name
        if(list[position].image.isNotEmpty()) {
            picasso.load(list[position].image).fit().into(holder.itemimage)
        }
        holder.parentitem.setOnClickListener {
            when (list[position].name) {

                "فروشگاه" -> {
                    val intent=Intent(activity,ShopActivity::class.java)
                    intent.putExtra("username",username)
                    intent.putExtra("imageprofile",imageuser)
                    intent.putExtra("nameuser",completenameuser)
                    activity.startActivity(intent)
                }
                "جایزه" -> {
                    gotoactivity(AwardActivity::class.java)
                }
                "نفرات برتر" -> {
                    fragmentRanking.activity=activity
                    activity.supportFragmentManager.beginTransaction().replace(R.id.frm_fragment,fragmentRanking).addToBackStack(null).commit()
                    bottomNavigationView.menu.getItem(3).isChecked=true

                }
                "تنظمیات" -> {
                    println("تنظیمات")
                    fragmentSetting.activity=activity
                    activity.supportFragmentManager.beginTransaction().replace(R.id.frm_fragment,fragmentSetting).addToBackStack(null).commit()
                    bottomNavigationView.menu.getItem(4).isChecked=true
                }
                "پیش بینی " -> {
                    val intent=Intent(activity,PredictActivity::class.java)
                    intent.putExtra("usernameuser",username)
                    activity.startActivity(intent)
                }


            }

        }


    }

    override fun getItemCount(): Int {
return list.size   }


    private fun gotoactivity(activityclass:Class<*>)
    {
        val intent=Intent(activity,activityclass)
        activity.startActivity(intent)

    }
}