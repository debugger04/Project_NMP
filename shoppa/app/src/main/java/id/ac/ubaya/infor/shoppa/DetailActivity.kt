package id.ac.ubaya.infor.shoppa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_product_home.view.*
import org.json.JSONObject
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {
    val PRODUCT_ID = ""
    var productID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbarDetail)
        supportActionBar?.title = "shoppatu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val q = Volley.newRequestQueue(this)
        val url = "http://10.0.2.2/nmp160418083/select_product_with_id.php"
        val stringRequest = object:StringRequest(Request.Method.POST, url,
            Response.Listener {
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
                        Picasso.get().load(produk.image).into(imageView)
                        txtNamaProduk.text = produk.nama
                        txtDescProduk.text = produk.deskripsi
                        var price = NumberFormat.getInstance().format(produk.harga)
                        txtHargaProduk.text = "Rp " + price
                        productID = produk.id
                    }
                }
            },
            Response.ErrorListener {
                Log.d("cekparams", it.message.toString()) }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = intent.getStringExtra(PRODUCT_ID).toString()
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