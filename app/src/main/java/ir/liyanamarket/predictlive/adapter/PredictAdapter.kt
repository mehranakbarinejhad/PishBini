package ir.liyanamarket.predictlive.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendinsertpredictInterface
import ir.liyanamarket.predictlive.dataclass.Match
import ir.liyanamarket.predictlive.dataclass.Predict
import ir.liyanamarket.predictlive.presenter.predict.PresenterApiConnectinsertPredict
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PredictAdapter(private val context: Context) :
    RecyclerView.Adapter<PredictAdapter.Customviewholder>(), KoinComponent {
    lateinit var listmatch: List<Match>
    lateinit var predictlist: List<Predict>
    lateinit var sendinsertpredictInterface: SendinsertpredictInterface
    lateinit var username: String
    private val picasso: Picasso by inject()
    private val presenterApiConnectinsertPredict:PresenterApiConnectinsertPredict by inject()

    inner class Customviewholder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {
        val textstatus: TextView = Itemview.findViewById(R.id.txt_status_predictrecyclerlist)
        val btnadd: Button = itemView.findViewById(R.id.btn_addpredict)
        val edittextuserhometeamgols: EditText = Itemview.findViewById(R.id.edt_userhometeamgol)
        val edittextuserguestteamgols: EditText = Itemview.findViewById(R.id.edt_userguestteamgol)
        val imagehometeam: ImageView = Itemview.findViewById(R.id.img_hometeam)
        val imageguestteam: ImageView = Itemview.findViewById(R.id.img_guestteam)
        val texthometeamname: TextView = Itemview.findViewById(R.id.txt_hometeamname)
        val textguesteamname: TextView = Itemview.findViewById(R.id.txt_guestteamname)
        val textadminhomegols: TextView = Itemview.findViewById(R.id.txtadminhomegoal)
        val textadminguestgols: TextView = Itemview.findViewById(R.id.txt_adminguestgol)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Customviewholder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.predictrecyclerlistitem, parent, false)
        return Customviewholder(view)
    }

    override fun onBindViewHolder(holder: Customviewholder, position: Int) {
        if (predictlist.count() == 0) {
            predictlist = listOf(Predict("", 0, 0, 0, ""))
        }

        //region Match To recycler
        applymatchtorecycler(
            position,
            holder.textstatus,
            holder.imagehometeam,
            holder.imageguestteam,
            holder.texthometeamname,
            holder.textguesteamname,
            holder.textadminhomegols,
            holder.textadminguestgols,
            holder.btnadd,
            holder.edittextuserhometeamgols,
            holder.edittextuserguestteamgols,
        )
        //endregion
        //region User To Recycler
        applyuserpredicttorecycler(
            predictlist,
            position,
            holder.btnadd,
            holder.edittextuserhometeamgols,
            holder.edittextuserguestteamgols
        )
        //endregion
        //region button Command To Recycler
        applubuttoncommand(
            holder.btnadd,
            holder.edittextuserhometeamgols,
            holder.edittextuserguestteamgols,
            username, position
        )
        //endregion

    }

    override fun getItemCount(): Int {


        return listmatch.size

    }


    //region Apply Match Details To Recycler Predict
    @SuppressLint("SetTextI18n")
    fun applymatchtorecycler(
        position: Int,
        txtstatus: TextView,
        imagehome: ImageView,
        imageguest: ImageView,
        txthometeamname: TextView,
        txtguestteamname: TextView,
        txtadminhomegols: TextView,
        txtadminguestgolas: TextView,
        btnok: Button,
        edtuserhomegoals: EditText,
        edtuserguestgols: EditText

    ) {
        if (listmatch[position].status != "") {
            txtstatus.text = listmatch[position].status

            txtadminhomegols.text = listmatch[position].homegols.toString()
            txtadminguestgolas.text = listmatch[position].teamguestgols.toString()
            btnok.setBackgroundResource(R.drawable.disablebuttonpredictlist)
            if (listmatch[position].status == "پایان") {
                btnok.text = "+0"
            } else {
                btnok.text = "غیرفعال"
            }

            btnok.isEnabled = false
        } else {
            val time = listmatch[position].matchhour
            val hour = time.substring(0, 2)
            val min = time.substring(2, 4)
            txtstatus.text = "$hour:$min"
            txtadminhomegols.text = "?"
            txtadminguestgolas.text = "?"
            edtuserhomegoals.isEnabled=true
            edtuserguestgols.isEnabled=true
        }
        picasso.load(listmatch[position].hometeam[0].logo).fit().centerCrop().into(imagehome)
        picasso.load(listmatch[position].guestteam[0].logo).fit().centerCrop().into(imageguest)
        txthometeamname.text = listmatch[position].hometeam[0].name
        txtguestteamname.text = listmatch[position].guestteam[0].name
    }
    //endregion


    //region Apply User Predict Details To Recycler Predict
    @SuppressLint("SetTextI18n")
    fun applyuserpredicttorecycler(
        listpredict: List<Predict>,
        position: Int,
        btnok: Button,
        edtuserhometeamgols: EditText,
        edtuserguestteamgols: EditText

    ) {
        var counter = 0

        while (counter < listpredict.count()) {
            if (listpredict[counter].matchid == listmatch[position].matchid) {

                if (listpredict[counter].scorematch != "") {
                    btnok.text = "+" + listpredict[counter].scorematch
                    btnok.isEnabled = false
                    when (listpredict[counter].scorematch) {
                        "2" -> {
                            btnok.setBackgroundResource(R.drawable.predictscore2shape)
                        }
                        "5" -> {
                            btnok.setBackgroundResource(R.drawable.predictscore5shape)
                        }
                        "7" -> {
                            btnok.setBackgroundResource(R.drawable.predictscore7shape)
                        }
                        "10" -> {
                            btnok.setBackgroundResource(R.drawable.predictscore10shape)
                        }
                    }


                } else {
                    if (listmatch[position].status == "") {
                        btnok.text = "ویرایش"
                        btnok.isEnabled = true
                        btnok.setBackgroundResource(R.drawable.editbuttnpredictshape)

                    } else {
                        if (listmatch[position].status != "END") {
                            btnok.text = "غیرفعال"
                            btnok.setBackgroundResource(R.drawable.disablebuttonpredictlist)
                            btnok.isEnabled = false
                        }

                    }
                }

                edtuserhometeamgols.setText(listpredict[counter].hometeamgols.toString())
               // edtuserhometeamgols.isEnabled = false

                edtuserguestteamgols.setText(listpredict[counter].guestteamgols.toString())
               // edtuserguestteamgols.isEnabled = false

                return
            }

            counter++
        }
    }
    //endregion

    //region Button Add Command
    @SuppressLint("SetTextI18n")
    fun applubuttoncommand(
        btnok: Button,
        edttextuserhomegoals: EditText,
        edttextuserguestgols: EditText,
        username: String,
        position: Int,
    ) {


        if(btnok.text=="ویرایش"){
            edttextuserhomegoals.isEnabled = false
            edttextuserguestgols.isEnabled = false
        }
        else if(btnok.text=="ثبت"){
            edttextuserhomegoals.isEnabled = true
            edttextuserguestgols.isEnabled = true
        }

        btnok.setOnClickListener {
            if (btnok.text == "ویرایش") {
                btnok.isEnabled = true
                btnok.text = "ثبت"
                edttextuserhomegoals.isEnabled = true
                edttextuserguestgols.isEnabled = true
                btnok.setBackgroundResource(R.drawable.predictitembuttonshape)
            } else if (btnok.text == "ثبت") {
                if (edttextuserhomegoals.text.isNotEmpty() && edttextuserguestgols.text.isNotEmpty()) {
                    btnok.isEnabled = true
                    btnok.text = "ویرایش"
                    edttextuserhomegoals.isEnabled = false
                    edttextuserguestgols.isEnabled = false
                    btnok.setBackgroundResource(R.drawable.editbuttnpredictshape)
                    presenterApiConnectinsertPredict.sendinsertpredictInterface=sendinsertpredictInterface
                    presenterApiConnectinsertPredict.insertpredict(username,listmatch[position].matchid.toString().toInt(),edttextuserhomegoals.text.toString().toInt(),edttextuserguestgols.text.toString().toInt())
                } else {
                    Toast.makeText(context, "Please Enter Goals", Toast.LENGTH_LONG).show()
                }

            }
        }

    }
    //endregion


}