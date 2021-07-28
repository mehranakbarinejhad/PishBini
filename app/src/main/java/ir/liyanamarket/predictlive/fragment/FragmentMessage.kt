package ir.liyanamarket.predictlive.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import ir.liyanamarket.predictlive.R

@Suppress("DEPRECATION")
class FragmentMessage:DialogFragment() {
    lateinit var message:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.messagefragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textmessage:TextView=requireView().findViewById(R.id.txt_message)
        textmessage.text=message

        val btnok:Button=requireView().findViewById(R.id.btn_message_ok)
        btnok.setOnClickListener {
            dismiss()
        }
    }
    override fun onStart() {
        super.onStart()
        val width=(resources.displayMetrics.widthPixels*0.85).toInt()
        dialog!!.window?.setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog!!.setCancelable(false)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}