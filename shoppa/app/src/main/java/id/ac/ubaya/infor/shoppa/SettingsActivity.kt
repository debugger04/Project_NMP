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
                val url = "http://ubaya.prototipe.net/nmp160418083/update_username.php"
                val stringRequest = object: StringRequest(
                    Request.Method.POST, url,
                    Response.Listener {
                        val obj = JSONObject(it)
                        if(obj.getString("result") == "OK") {
                            Toast.makeText(this, "Username has been successfully changed", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "This username is not available", Toast.LENGTH_SHORT).show()
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
            if (new_pass_1.text.toString() != "" && new_pass_2.text.toString() != "") {
                if (new_pass_1.text.toString() == new_pass_2.text.toString()) {
                    val q = Volley.newRequestQueue(this)
                    val url = "http://ubaya.prototipe.net/nmp160418083/update_password.php"
                    val stringRequest = object: StringRequest(
                        Request.Method.POST, url,
                        Response.Listener {
                            val obj = JSONObject(it)
                            if(obj.getString("result") == "OK") {
                                Toast.makeText(this, "Password has been successfully changed", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(this, "Old password did not match !", Toast.LENGTH_SHORT).show()
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
                            params["old_pass"] = old_pass.text.toString()
                            params["new_pass"] = new_pass_1.text.toString()
                            return params
                        }
                    }
                    q.add(stringRequest)
                } else {
                    Toast.makeText(this, "New Password does not match its confirmation", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "New Password cannot be empty !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}