package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject
import java.text.NumberFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val USER_ID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val q = Volley.newRequestQueue(activity)
        val url = "http://10.0.2.2/nmp160418083/select_profile.php"
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener {
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val prodObj = data.getJSONObject(i)
                        Picasso.get().load(prodObj.getString("image")).into(imgProfile)
                        var saldo = NumberFormat.getInstance().format(prodObj.getInt("saldo"))
                        txtUang.text = "Rp " + saldo
                        txtNama.text = prodObj.getString("username")
                        txtMember.text = "Member : " + prodObj.getString("jenis")
                        var checkout = NumberFormat.getInstance().format(prodObj.getInt("checkout"))
                        count_checkout.text = checkout.toString()
                        count_points.text = prodObj.getString("poin")
                    }
                }
                Log.d("cekparams", obj.toString())
            },
            Response.ErrorListener {
                Log.d("cekparams", it.message.toString()) }
        )
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                var sharedFile = "id.ac.ubaya.infor.shoppa"
                var shared: SharedPreferences = activity!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                params["id_user"] = shared.getInt(USER_ID, 0).toString()
                return params
            }
        }
        q.add(stringRequest)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTopUp.setOnClickListener{
            val intent = Intent(activity, TopupActivity::class.java)
            activity?.startActivity(intent)
        }

        btnSettings.setOnClickListener{
            val intent = Intent(activity, SettingsActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}