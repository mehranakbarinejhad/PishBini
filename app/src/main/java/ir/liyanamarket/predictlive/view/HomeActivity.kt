package ir.liyanamarket.predictlive.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.db.FunctionDataBase
import ir.liyanamarket.predictlive.fragment.FragmentHome
import ir.liyanamarket.predictlive.fragment.FragmentRanking
import ir.liyanamarket.predictlive.fragment.FragmentSetting
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject



class HomeActivity : AppCompatActivity() {
    private var firstpresstime = 0L
    private val maxtime = 2000L
    private val fragmenthome: FragmentHome by inject()
    private val fragmentranking: FragmentRanking by inject()
    private val fragmentSetting: FragmentSetting by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR

        fragmenthome.activity2 = this
        fragmenthome.bottomNavigationView=bottomnavigationview
        fragmentranking.activity = this
        fragmentSetting.activity=this
        addfragment(fragmenthome)

        bottomnavigationview.background = null
        bottomnavigationview.menu.getItem(2).isEnabled = false
        bottomnavigationview.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.mnuhome -> {
                    addfragment(fragmenthome)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.mnurank -> {
                    addfragment(fragmentranking)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.mnusetting -> {
                    addfragment(fragmentSetting)
                    return@setOnNavigationItemSelectedListener true
                }
                 R.id.mnushop -> {
                     val intent = Intent(this.applicationContext, ShopActivity::class.java)
                     intent.putExtra(
                         "imageprofile",
                        intent.getStringExtra("imageloginuser").toString()
                     )
                     intent.putExtra("nameuser",intent.getStringExtra("nameuser").toString())
                     startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }


            }
            false
        }

        fab.setOnClickListener {
            val homeIntent = Intent(applicationContext, PredictActivity::class.java)

            homeIntent.putExtra(
                "usernameuser",
                intent.getStringExtra("usernameloginuser").toString()
            )
            startActivity(homeIntent)

        }
    }


    override fun onBackPressed() {
        if (firstpresstime + maxtime > System.currentTimeMillis()) {
            finish()

        } else {

            Toast.makeText(this, "برای خروج دوبار کلیک کنید.", Toast.LENGTH_SHORT).show()
            firstpresstime = System.currentTimeMillis()
        }
    }

    private fun addfragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frm_fragment, fragment)
            .addToBackStack(null).commit()

    }

}