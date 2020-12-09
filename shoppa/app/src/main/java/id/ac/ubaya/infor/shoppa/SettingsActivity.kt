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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_topup.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject
import java.text.NumberFormat

class SettingsActivity : AppCompatActivity() {
    val USER_ID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbarSettings)
        supportActionBar?.title = "shoppatu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnChangeUsername.setOnClickListener {
            if (new_username.text.toString() != "") {
                val q = Volley.newRequestQueue(this)
                val url = "http://10.0.2.2/nmp160418083/update_username.php"
                val stringRequest = object: StringRequest(
                    Request.Method.POST, url,
                    Response.Listener {
                        val obj = JSONObject(it)
                        if(obj.getString("result") == "OK") {
                            Toast.makeText(this, "Username has been successfully changed", Toast.LENGTH_SHORT).show()
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
                        params["new_username"] = new_username.text.toString()
                        return params
                    }
                }
                q.add(stringRequest)
            } else {
                Toast.makeText(this, "New Username cannot be empty !", Toast.LENGTH_SHORT).show()
            }
        }

        btnChangePass.setOnClickListener {

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}