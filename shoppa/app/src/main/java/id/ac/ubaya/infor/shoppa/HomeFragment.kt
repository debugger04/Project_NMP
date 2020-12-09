package id.ac.ubaya.infor.shoppa

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var categories:ArrayList<Category> = ArrayList()
    var products:ArrayList<Product> = ArrayList()
    var v:View ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val q1 = Volley.newRequestQueue(activity)
        val url1 = "http://10.0.2.2/nmp160418083/select_category.php"
        var stringRequest1 = StringRequest(
            Request.Method.POST, url1,
            Response.Listener<String> {
                Log.d("msg", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val catObj = data.getJSONObject(i)
                        val kategori = Category(
                            catObj.getInt("id"),
                            catObj.getString("nama"),
                            catObj.getString("image")
                        )
                        categories.add(kategori)
                    }
                    updateSlider()
                    Log.d("list_category", categories.toString())
                }
            },
            Response.ErrorListener {
                Log.e("msg", it.message.toString())
            })
        q1.add(stringRequest1)

        val q2 = Volley.newRequestQueue(activity)
        val url2 = "http://10.0.2.2/nmp160418083/select_product.php"
        var stringRequest2 = StringRequest(
            Request.Method.POST, url2,
            Response.Listener<String> {
                Log.d("msg", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val prodObj = data.getJSONObject(i)
                        val produk = Product(
                            prodObj.getInt("id"),
                            prodObj.getString("nama"),
                            prodObj.getInt("harga"),
                            prodObj.getString("deskripsi"),
                            prodObj.getInt("likes"),
                            prodObj.getString("image"),
                            0
                        )
                        products.add(produk)
                    }
                    updateList()
                    Log.d("list_produk", products.toString())
                }
            },
            Response.ErrorListener {
                Log.e("msg", it.message.toString())
            })
        q2.add(stringRequest2)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }

    fun updateSlider() {
        val lm:LinearLayoutManager = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        var recyclerView = v?.findViewById<RecyclerView>(R.id.recycler_category)
        recyclerView?.layoutManager = lm
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = CategoryAdapter(categories, this)
    }

    fun updateList() {
        val sg:StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var recyclerView2 = v?.findViewById<RecyclerView>(R.id.recycler_product)
        recyclerView2?.layoutManager = sg
        recyclerView2?.setHasFixedSize(true)
        recyclerView2?.adapter = HomeProductAdapter(products, this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}