package id.ac.ubaya.infor.shoppa

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setSupportActionBar(toolbarSignUp)
        supportActionBar?.title = "shoppatu"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnSignupNow.setOnClickListener {
            var inUsername = inputUsername.text.toString()
            var inEmail = inputEmail.text.toString()
            var inPass1 = inputPass1.text.toString()
            var inPass2 = inputPass2.text.toString()
            var inUrl = inputURL.text.toString()

            var snackBar = Snackbar.make(this.btnSignupNow, "", Snackbar.LENGTH_SHORT).setAction("Action", null)
            snackBar.setActionTextColor(Color.WHITE)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(Color.DKGRAY)
            val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.WHITE)

            if (inUsername != "" && inEmail != "" && inPass1 != "" && inPass2 != "") {
                if (inPass1 == inPass2) {
                    val q = Volley.newRequestQueue(this)
                    val url = "http://ubaya.prototipe.net/nmp160418083/register.php"
                    val stringRequest = object: StringRequest(
                        Request.Method.POST, url,
                        Response.Listener {
                            Log.d("msg", it)
                            val obj = JSONObject(it)
                            if(obj.getString("result") == "OK") {
                                Toast.makeText(this, "Your register request has been approved. You may login now", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                snackBar = Snackbar.make(this.btnSignupNow, "Email / Username has been used before", Snackbar.LENGTH_SHORT).setAction("Action", null)
                                snackBar.show()
                            }
                        },
                        Response.ErrorListener {
                            Log.d("cekparams", it.message.toString()) }
                    )
                    {
                        override fun getParams(): MutableMap<String, String> {
                            val params = HashMap<String, String>()
                            params["username"] = inputUsername.text.toString()
                            params["email"] = inputEmail.text.toString()
                            params["password"] = inputPass1.text.toString()
                            params["img"] = inUrl
                            return params
                        }
                    }
                    q.add(stringRequest)
                }
                else {
                    snackBar = Snackbar.make(this.btnSignupNow, "Input password must be the same", Snackbar.LENGTH_SHORT).setAction("Action", null)
                    snackBar.show()
                }
            }
            else {
                snackBar = Snackbar.make(this.btnSignupNow, "Please fill all the blank field first", Snackbar.LENGTH_SHORT).setAction("Action", null)
                snackBar.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}