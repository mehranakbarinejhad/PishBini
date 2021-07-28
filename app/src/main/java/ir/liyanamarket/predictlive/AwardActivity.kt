package ir.liyanamarket.predictlive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import ir.liyanamarket.predictlive.`interface`.SendAwardText
import ir.liyanamarket.predictlive.dataclass.Award
import ir.liyanamarket.predictlive.presenter.award.PresenterApiConnectAward
import kotlinx.android.synthetic.main.activity_award.*
import org.koin.android.ext.android.inject

class AwardActivity : AppCompatActivity(),SendAwardText {
    private val presenterApiConnectAward:PresenterApiConnectAward by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_award)
        presenterApiConnectAward.sendAwardText=this
        presenterApiConnectAward.showtext()
        txt_award.animation=AnimationUtils.loadAnimation(this,R.anim.fadetextaward)
    }

    override fun onsuccess(award: Award) {
        txt_award.text=award.text

    }

    override fun onerror(t: Throwable) {
        txt_award.text="خطا در دریافت اطلاعات"

    }

}