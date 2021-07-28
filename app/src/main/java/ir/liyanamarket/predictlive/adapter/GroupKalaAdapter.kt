package ir.liyanamarket.predictlive.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendSelectKalaInterface
import ir.liyanamarket.predictlive.`interface`.sendgroupselected
import ir.liyanamarket.predictlive.dataclass.GroupKala
import ir.liyanamarket.predictlive.dataclass.Kala
import ir.liyanamarket.predictlive.presenter.shop.kala.PresenterApiConnectSelectKala
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

//import org.koin.core.KoinComponent
//import org.koin.core.inject

class GroupKalaAdapter:KoinComponent,RecyclerView.Adapter<GroupKalaAdapter.CustomViewHolder>(),SendSelectKalaInterface{
    private val picasso:Picasso by inject()
    lateinit var list:List<GroupKala>
    private var index:Int=0
    private val presenterApiConnectSelectKala: PresenterApiConnectSelectKala by inject()
    lateinit var recyclerView: RecyclerView
    private val kalaAdapter:KalaAdapter by inject()
    lateinit var sort:String
lateinit var sendgroupselected: sendgroupselected


    inner class CustomViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
       val txtnamegroup:TextView=itemview.findViewById(R.id.txt_namegroup)
        val viewimageshape:View=itemview.findViewById(R.id.view_imageshape)
        val imagegroup:ImageView=itemview.findViewById(R.id.img_group)
        val groupparent:RelativeLayout=itemview.findViewById(R.id.groupkala_parent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

val view=LayoutInflater.from(parent.context).inflate(R.layout.groupkalarecycleritem,parent,false)
        return CustomViewHolder(view)

    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CustomViewHolder,position: Int) {
        holder.txtnamegroup.text=list[position].namegroup
        picasso.load(list[position].imagegroup).fit().into(holder.imagegroup)
        holder.viewimageshape.setOnClickListener {
            presenterApiConnectSelectKala.sendSelectKalaInterface = this
            var groupkalaname=list[position].namegroup
            if(groupkalaname=="همه")
            {
                groupkalaname="All"
            }
            presenterApiConnectSelectKala.selectkala(groupkalaname,sort)
            println(groupkalaname+sort)
            sendgroupselected.groupactive(groupkalaname)
          index=position
            notifyDataSetChanged()
        }




        when(list[position].id) {
            "1" ->holder.viewimageshape.setBackgroundResource(R.drawable.shaperecyclerkalaitemgreen)
            "2"->holder.viewimageshape.setBackgroundResource(R.drawable.shaperecyclerkalaitemblue)
            "3"->holder.viewimageshape.setBackgroundResource(R.drawable.shaperecyclerkalaitemorange)
            "4"->holder.viewimageshape.setBackgroundResource(R.drawable.shaperecyclerkalaitemred)
            "5"->holder.viewimageshape.setBackgroundResource(R.drawable.shaperecyclerkalaitemdark)
            "6"->holder.viewimageshape.setBackgroundResource(R.drawable.shaperecyclerkalaitembrown)
            "7"->holder.viewimageshape.setBackgroundResource(R.drawable.shaperecyclerkalaitemblue)

        }
       if(index==position)
        {
            holder.groupparent.setBackgroundResource(R.drawable.viewimageshapegroupbackground)
        }
        else
        {
            holder.groupparent.setBackgroundResource(R.drawable.shaperecyclerkalaitempink)

        }


    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onsuccess(list: MutableList<Kala>) {
        kalaAdapter.list = list
        recyclerView.apply {
            layoutManager =
                GridLayoutManager(context.applicationContext,2, GridLayoutManager.VERTICAL, false)
            adapter = kalaAdapter
        }
    }

    override fun onerror(t: Throwable) {
    }

}
