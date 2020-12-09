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
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.toolbarDetail
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_topup.*
import org.json.JSONObject

class TopupActivity : AppCompatActivity() {
    val USER_ID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)
        setSupportActionBar(toolbarTopUp)
        supportActionBar?.title = "shoppatu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnConfirmTopUp.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "http://10.0.2.2/nmp160418083/update_saldo.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        Toast.makeText(this, "Top up has been successfully done !", Toast.LENGTH_SHORT).show()
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
                    params["saldo"] = txtAmount.text.toString()
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