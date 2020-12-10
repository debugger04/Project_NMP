package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_cart.*
import org.json.JSONObject
import java.text.NumberFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var products:ArrayList<Product> = ArrayList()
    val USER_ID = ""
    var v:View ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_cart, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCheckout.setOnClickListener {
            val intent = Intent(activity, CheckOutActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(activity, "TEST", Toast.LENGTH_SHORT).show()
        products.clear()
        val q = Volley.newRequestQueue(activity)
        val url = "http://10.0.2.2/nmp160418083/select_cart.php"
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener {
                Log.d("msg", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val data_any = obj.getJSONArray("data_any")
                    for(i in 0 until data.length()) {
                        val prodObj = data.getJSONObject(i)
                        val produk = Product(
                            prodObj.getInt("id"),
                            prodObj.getString("nama"),
                            prodObj.getInt("harga"),
                            prodObj.getString("deskripsi"),
                            prodObj.getInt("likes"),
                            prodObj.getString("image"),
                            prodObj.getInt("qty"),
                        )
                        products.add(produk)
                    }
                    for(i in 0 until data_any.length()) {
                        val analytic = data_any.getJSONObject(i)
                        jmlItem.text = "Jumlah Item : " + analytic.getInt("jml_barang").toString()
                        var hargaTot = NumberFormat.getInstance().format(analytic.getInt("grand_total"))
                        jmlHarga.text = "Rp " + hargaTot
                    }
                    updateList()
                    Log.d("list_cart", products.toString())
                }
            },
            Response.ErrorListener {
                Log.d("cekparams", it.message.toString()) }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                var sharedFile = "id.ac.ubaya.infor.shoppa"
                var shared: SharedPreferences = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                params["id_user"] = shared.getInt(USER_ID, 0).toString()
                return params
            }
        }
        q.add(stringRequest)
    }

    fun updateList() {
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        var recyclerView = v?.findViewById<RecyclerView>(R.id.recyclerViewCart)
        recyclerView?.layoutManager = lm
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = CartProductAdapter(products, this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CartFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}