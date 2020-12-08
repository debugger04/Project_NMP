package id.ac.ubaya.infor.shoppa

import android.content.Intent
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn.setOnClickListener{
            val q = Volley.newRequestQueue(this)
            val url = "http://10.0.2.2/nmp160418083/login.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    Log.d("msg", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
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