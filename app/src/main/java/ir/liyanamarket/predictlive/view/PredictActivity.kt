package ir.liyanamarket.predictlive.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.`interface`.SendSelectMatchInterface
import ir.liyanamarket.predictlive.`interface`.SendSelectPredictInterface
import ir.liyanamarket.predictlive.`interface`.SendinsertpredictInterface
import ir.liyanamarket.predictlive.adapter.PredictAdapter
import ir.liyanamarket.predictlive.dataclass.Match
import ir.liyanamarket.predictlive.dataclass.Predict
import ir.liyanamarket.predictlive.dataclass.ResultPredict
import ir.liyanamarket.predictlive.presenter.match.PresenterApiConnectSelectMatch
import ir.liyanamarket.predictlive.presenter.predict.PresenterApiConnectSelectPredict
import kotlinx.android.synthetic.main.activity_predict.*
import org.koin.android.ext.android.inject

class PredictActivity : AppCompatActivity(),SendSelectPredictInterface,SendSelectMatchInterface,SendinsertpredictInterface {
    private val presenterApiConnectSelectPredict:PresenterApiConnectSelectPredict by inject()
    private val presenterApiConnectSelectMatch:PresenterApiConnectSelectMatch by inject()
    private val predictAdapter:PredictAdapter by inject()
   lateinit var username: String
    var predictlist= listOf<Predict>( )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_predict)
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR
        username=intent.getStringExtra("usernameuser").toString()
predictAdapter.username=username
        predictAdapter.sendinsertpredictInterface=this

        presenterApiConnectSelectPredict.sendSelectPredictInterface=this
            presenterApiConnectSelectPredict.selectPredict(username)
    }








    override fun onsuccessPredict(list: List<Predict>) {
        try {
            predictlist = list
            presenterApiConnectSelectMatch.sendSelectMatchInterface = this
            presenterApiConnectSelectMatch.getmatch("000128")
        }
        catch (ex:Exception){}
    }

    override fun onerrorMatch(t: Throwable) {
        Toast.makeText(this,"Error Match",Toast.LENGTH_LONG).show()

    }

    override fun onsuccessMatch(list: List<Match>) {

        predictAdapter.predictlist=predictlist
        predictAdapter.listmatch=list

        Recycler_MatchList.apply {
            layoutManager=LinearLayoutManager(this@PredictActivity,LinearLayoutManager.VERTICAL,false)
            adapter=predictAdapter
        }

    }

    override fun onerrorPredict(t: Throwable) {
            Toast.makeText(this,"Error Predict",Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onsuccess(list: List<ResultPredict>) {

    }

    override fun onerror(t: Throwable) {

    }
}