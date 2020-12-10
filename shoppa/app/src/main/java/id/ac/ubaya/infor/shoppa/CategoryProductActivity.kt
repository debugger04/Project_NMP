package id.ac.ubaya.infor.shoppa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_category_product.*
import kotlinx.android.synthetic.main.activity_detail_history.*
import org.json.JSONObject
import java.text.NumberFormat

class CategoryProductActivity : AppCompatActivity() {
    val CATEGORY_ID = ""
    var products:ArrayList<Product> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_product)
        setSupportActionBar(toolbarCategoryProd)
        supportActionBar?.title = "shoppatu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val q = Volley.newRequestQueue(this)
        val url = "http://10.0.2.2/nmp160418083/select_product_with_category.php"
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener {
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
                    val data_arr = obj.getJSONArray("nama_cat")
                    for(i in 0 until data_arr.length()) {
                        val obj = data_arr.getJSONObject(i)
                        textView12.text = obj.getString("nama")
                    }
                    val sg: StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    var recyclerView2 = findViewById<RecyclerView>(R.id.recyclerCatProd)
                    recyclerView2?.layoutManager = sg
                    recyclerView2?.setHasFixedSize(true)
                    recyclerView2?.adapter = CategoryProductAdapter(products, this)
                }
            },
            Response.ErrorListener {
                Log.d("cekparams", it.message.toString()) }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["category_id"] = intent.getStringExtra(CATEGORY_ID).toString()
                return params
            }
        }
        q.add(stringRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}