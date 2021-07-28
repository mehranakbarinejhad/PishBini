package ir.liyanamarket.predictlive.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.view.DetailsKalaActivity
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.dataclass.Kala
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.DecimalFormat

class KalaAdapter( var context:Context) : RecyclerView.Adapter<KalaAdapter.Customviewholder>(),KoinComponent {
    lateinit var username:String
    lateinit var activity:AppCompatActivity
    private val picasso:Picasso by inject()
    lateinit var list: MutableList<Kala>

    inner class Customviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val txttitrkala: TextView = itemview.findViewById(R.id.txt_titrkala)
        val txtnamekala: TextView = itemview.findViewById(R.id.txt_namekala)
      //  val imageflagh: ImageView = itemview.findViewById(R.id.img_flagkala)
        val imgkala: ImageView = itemview.findViewById(R.id.img_kala)
        val price: TextView = itemview.findViewById(R.id.txt_price)
        val recyclerkalaparent:RelativeLayout=itemview.findViewById(R.id.recyclerkalaparent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Customviewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.kalaitemrecycler, parent, false)
        return Customviewholder(view)
    }

    override fun onBindViewHolder(holder: Customviewholder, position: Int) {
        holder.recyclerkalaparent.animation=AnimationUtils.loadAnimation(context.applicationContext,R.anim.fade_transtion_animation)
        holder.txttitrkala.text = list[position].titr
        holder.txtnamekala.text = list[position].name
    //    picasso.load(list[position].Countryimage).fit().centerCrop().into(holder.imageflagh)
        picasso.load(list[position].image).fit().into(holder.imgkala)
        holder.price.text= DecimalFormat("###,###,###").format(list[position].price.toLong())
        when((1..7).random()) {
            1 ->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemgreen)
            2->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemblue)
            3->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemorange)
            4->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemred)
            5->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitemdark)
            6->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitempink)
           7->holder.recyclerkalaparent.setBackgroundResource(R.drawable.shaperecyclerkalaitembrown)

        }
        holder.recyclerkalaparent.setOnClickListener {
            val intent=Intent(activity, DetailsKalaActivity::class.java)
            intent.putExtra("codekala",list[position].id)
            intent.putExtra("image",list[position].image)
            intent.putExtra("price",list[position].price)
            intent.putExtra("titr",list[position].titr)
            intent.putExtra("details",list[position].details)
            intent.putExtra("description",list[position].description)
            intent.putExtra("size",list[position].size)
            intent.putExtra("username",username)
            activity.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }




}