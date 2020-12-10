package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_history.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject
import java.text.NumberFormat

class DetailHistoryActivity : AppCompatActivity() {
    val NO_INVOICE = ""
    var details:ArrayList<TransactionDetail> = ArrayList()
    var no_invoice = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
        setSupportActionBar(toolbarDetailHistory)
        supportActionBar?.title = "shoppatu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtNomorInvoice.text = "INVOICE : " + intent.getStringExtra(NO_INVOICE)
        no_invoice = intent.getStringExtra(NO_INVOICE).toString()

        val q = Volley.newRequestQueue(this)
        val url = "http://ubaya.prototipe.net/nmp160418083/select_nota_detail.php"
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener {
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val detObj = data.getJSONObject(i)
                        val detail = TransactionDetail(
                            detObj.getString("nama"),
                            detObj.getString("image"),
                            detObj.getInt("harga"),
                            detObj.getInt("qty")
                        )
                        details.add(detail)
                    }
                    val data_any = obj.getJSONArray("data_any")
                    for(i in 0 until data_any.length()) {
                        val det = data_any.getJSONObject(i)
                        txtJumlahItems.text = "Items : " + det.getInt("jml")
                        txtTglTransaksi.text = "Tanggal : " + det.getString("tgl_order")
                        txtGrandTotal.text = "Rp " + NumberFormat.getInstance().format(det.getInt("grandtotal"))
                    }
                    val lm: LinearLayoutManager = LinearLayoutManager(this)
                    var recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCart)
                    recyclerView?.layoutManager = lm
                    recyclerView?.setHasFixedSize(true)
                    recyclerView?.adapter = TransactionDetailAdapter(details)
                }
            },
            Response.ErrorListener {
                Log.d("cekparams", it.message.toString()) }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["no_invoice"] = no_invoice
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