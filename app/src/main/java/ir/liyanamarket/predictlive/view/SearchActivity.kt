package ir.liyanamarket.predictlive.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import ir.liyanamarket.predictlive.R
import ir.liyanamarket.predictlive.adapter.SearchkalaAdapter
import ir.liyanamarket.predictlive.dataclass.Kala
import ir.liyanamarket.predictlive.utils.FilterRecyclerViewKala
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_shop.*
import org.koin.android.ext.android.inject
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
class SearchActivity : AppCompatActivity(){
    private val filterRecyclerViewKala: FilterRecyclerViewKala by inject()
    private val searchkalaAdapter: SearchkalaAdapter by inject()
    private var arraylist = ArrayList<Kala>()
    private val displaylist=ArrayList<Kala>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        window.decorView.layoutDirection= View.LAYOUT_DIRECTION_LTR

        searchview_kala.requestFocus()
        searchview_kala.onActionViewExpanded()

            val listkala=intent.getSerializableExtra("listf")
        arraylist.addAll(listkala as ArrayList<Kala>)
        searchkalaAdapter.list=displaylist
        recyclerkala_searchactivity.apply {
            layoutManager=GridLayoutManager(this@SearchActivity,2,GridLayoutManager.VERTICAL,false)
            adapter=searchkalaAdapter
        }
        searchview_kala.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                filterRecyclerViewKala.filter(p0.toString(),arraylist,displaylist,recyclerkala_searchactivity)
                return true
            }

        })


    }


}

