package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import org.json.JSONObject
import java.text.NumberFormat

class LoginActivity : AppCompatActivity() {
    val USER_ID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn.setOnClickListener{
            val q = Volley.newRequestQueue(this)
            val url = "http://ubaya.prototipe.net/nmp160418083/login.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("msg", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")
                        var get_id_user = 0
                        for(i in 0 until data.length()) {
                            val userObj = data.getJSONObject(i)
                            get_id_user = userObj.getInt("id")
                        }
                        var sharedFile = "id.ac.ubaya.infor.shoppa"
                        var shared:SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE )
                        var editor:SharedPreferences.Editor = shared.edit()
                        editor.putInt(USER_ID, get_id_user)
                        editor.apply()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val snackBar = Snackbar.make(this.btnSignIn,
                            "Your username or password is incorrect ! Please check again :)",
                            Snackbar.LENGTH_SHORT).setAction("Action", null)
                        snackBar.setActionTextColor(Color.WHITE)
                        val snackBarView = snackBar.view
                        snackBarView.setBackgroundColor(Color.DKGRAY)
                        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                        textView.setTextColor(Color.WHITE)
                        snackBar.show()
                    }
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString()) }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = txtUsername.text.toString()
                    params["password"] = txtPassword.text.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }

        btnSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}