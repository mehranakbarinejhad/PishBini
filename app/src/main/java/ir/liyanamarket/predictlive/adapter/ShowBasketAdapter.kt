package ir.liyanamarket.predictlive.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendDeleteBasket
import ir.liyanamarket.predictlive.`interface`.SendEditTedadKala
import ir.liyanamarket.predictlive.`interface`.Sendsumprice
import ir.liyanamarket.predictlive.dataclass.ShowBasket
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectDeleteBasket
import ir.liyanamarket.predictlive.presenter.shop.basket.PresenterApiConnectEDitTedadKala
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.DecimalFormat

class ShowBasketAdapter:KoinComponent,RecyclerView.Adapter<ShowBasketAdapter.Customviewholder>(),SendEditTedadKala,SendDeleteBasket {

    lateinit var list:List<ShowBasket>
    private val picasso:Picasso by inject()
    private val presenterApiConnectEDitTedadKala:PresenterApiConnectEDitTedadKala by inject()
    private val presenterApiConnectDeleteBasket:PresenterApiConnectDeleteBasket by inject()
    lateinit var username:String
lateinit var sendsumprice:Sendsumprice
    inner class Customviewholder(itemview:View):RecyclerView.ViewHolder(itemview){
        val imagekala:ImageView=itemview.findViewById(R.id.img_kala_basketactivity)
        val titrkala:TextView=itemview.findViewById(R.id.txt_titr_basketactivity)
        val sizekala:TextView=itemview.findViewById(R.id.txt_text_size_basketactivity)
        val imageclose:ImageView=itemview.findViewById(R.id.img_close)
        val pricekala:TextView=itemview.findViewById(R.id.txt_price_basketactivity)
        val tedadkala:TextView=itemview.findViewById(R.id.txt_tedad_basketactivity)
        val txtPlus:TextView=itemview.findViewById(R.id.txt_pluskala_basketactivity)
         val txtminez:TextView=itemview.findViewById(R.id.txt_minezkala_basketactivity)
            val txtprice:TextView=itemview.findViewById(R.id.txt_price_basketactivity)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Customviewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.basketitemrecycler,parent,false)
        return Customviewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Customviewholder, position: Int) {
        try{

            picasso.load(list[position].kala[0].image).fit().into(holder.imagekala)
            holder.titrkala.text=list[position].kala[0].titr
            holder.sizekala.text="سایز:"+list[position].sizekala
            val price=((list[position].kala[0].price.toLong())*(list[position].tedadkala.toLong()))
            holder.pricekala.text=DecimalFormat("###,###,###").format(price)
            holder.tedadkala.text=list[position].tedadkala
            holder.txtPlus.setOnClickListener {
               val tedad=list[position].tedadkala.toInt()+1

                    presenterApiConnectEDitTedadKala.sendEditTedadKala=this
                 presenterApiConnectEDitTedadKala.edittedadkala(username,list[position].codekala ,list[position].sizekala,tedad.toString())


            }
            holder.txtminez.setOnClickListener {


                val tedad=list[position].tedadkala.toInt()-1

                if(tedad>=1) {
                    presenterApiConnectEDitTedadKala.sendEditTedadKala = this
                    presenterApiConnectEDitTedadKala.edittedadkala(
                        username,
                        list[position].codekala,
                        list[position].sizekala,
                        tedad.toString()

                    )
                }



                }


            holder.imageclose.setOnClickListener {
                presenterApiConnectDeleteBasket.sendDeleteBasket=this
                presenterApiConnectDeleteBasket.deletebasket(username,list[position].codekala,list[position].sizekala)


            }



        }catch (ex:Exception){}

    }

    override fun getItemCount(): Int {
   return list.size
    }

    override fun onsuccessEditTedadKala(list: List<ShowBasket>) {
        try {
            sendsumprice.sendsum(fnSumPrice(list))
            this.list = list
            notifyDataSetChanged()
        }
        catch (ex:Exception){}


    }

    override fun onerrorEditTedadKala(t: Throwable) {

    }

    override fun onsuccessDeleteBasket(list: List<ShowBasket>) {
        try {
            sendsumprice.sendsum(fnSumPrice(list))

            this.list = list
            notifyDataSetChanged()
        }
        catch (ex:Exception){}    }

    override fun onerrorDeleteBasket(t: Throwable) {

    }



private fun fnSumPrice(list: List<ShowBasket>):String{
    var sum=0L
    var rowindex=list.size-1
    while (rowindex>=0)
    {
        sum+=((list[rowindex].kala[0].price.toLong())*(list[rowindex].tedadkala.toLong()))
        rowindex--
    }
    return DecimalFormat("###,###,###").format(sum)
}

}

