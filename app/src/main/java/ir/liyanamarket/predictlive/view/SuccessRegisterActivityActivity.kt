package ir.liyanamarket.predictlive.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import ir.liyanamarket.predictlive.R
import kotlinx.android.synthetic.main.activity_success_register_activity.*


class SuccessRegisterActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_register_activity)
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR


        img_success_successactivity.animation=AnimationUtils.loadAnimation(this, R.anim.zoomout)
        btn_gotologinactivity.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

}