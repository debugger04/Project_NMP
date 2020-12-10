package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_category_product.*
import kotlinx.android.synthetic.main.activity_check_out.*
import kotlinx.android.synthetic.main.activity_settings.*
import org.json.JSONObject
import java.text.NumberFormat

class CheckOutActivity : AppCompatActivity() {
    val USER_ID = ""
    var hargaAfter = 0
    var poin = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)
        setSupportActionBar(toolbarCheckOut)
        supportActionBar?.title = "shoppatu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val q = Volley.newRequestQueue(this)
        val url = "http://10.0.2.2/nmp160418083/select_before_checkout.php"
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener {
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val info = data.getJSONObject(i)
                        txtJenisMember.text = info.getString("member")
                        txtJmlDisc.text = info.getInt("diskon").toString() + "%"
                        txtJumlahItem.text = info.getInt("jml").toString()
                        txtHargaBefore.text = "Rp " + NumberFormat.getInstance().format(info.getInt("gtot"))
                        hargaAfter = info.getInt("gtot") - ((info.getInt("gtot") * info.getInt("diskon"))/100)
                        txtHargaAfter.text = "Rp " + NumberFormat.getInstance().format(hargaAfter)
                        poin = info.getInt("gtot")/1000
                        txtJumlahPoin.text = poin.toString()
                    }
                }
            },
            Response.ErrorListener {
                Log.d("cekparams", it.message.toString()) }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                var sharedFile = "id.ac.ubaya.infor.shoppa"
                var shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                params["id_user"] = shared.getInt(USER_ID, 0).toString()
                return params
            }
        }
        q.add(stringRequest)

        btnPay.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "http://10.0.2.2/nmp160418083/checkout.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        Toast.makeText(this, "Transaction has been done !", Toast.LENGTH_SHORT).show()
//                        finish()
                    } else {
                        Toast.makeText(this, "Your e-money amount is not enough ! Please top up first.", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString()) }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    var sharedFile = "id.ac.ubaya.infor.shoppa"
                    var shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                    params["id_user"] = shared.getInt(USER_ID, 0).toString()
                    params["grandtot"] = hargaAfter.toString()
                    params["poin"] = poin.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}