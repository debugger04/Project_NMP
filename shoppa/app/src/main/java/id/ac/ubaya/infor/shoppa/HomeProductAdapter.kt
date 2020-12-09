package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.layout_product_cart.view.*
import kotlinx.android.synthetic.main.layout_product_home.view.*
import org.json.JSONObject
import java.text.NumberFormat

class HomeProductAdapter(var products: ArrayList<Product>, val fragment: HomeFragment):
    RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder>() {
    class HomeProductViewHolder(var v: View, val fragment: HomeFragment):RecyclerView.ViewHolder(v)

    val PRODUCT_ID = ""
    val USER_ID = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_home, parent,false)
        return HomeProductAdapter.HomeProductViewHolder(v, fragment)
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        val url = products[position].image
        Picasso.get().load(url).into(holder.v.imgView)
        holder.v.productName.text = products[position].nama
        holder.v.productDesc.text = products[position].deskripsi
        var price = NumberFormat.getInstance().format(products[position].harga)
        holder.v.productPrice.text = "Rp " + price

        holder.v.btnAddToCart.setOnClickListener {
            val q = Volley.newRequestQueue(fragment.context)
            val url = "http://10.0.2.2/nmp160418083/add_to_cart.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        Toast.makeText(fragment.context, "Item has been added to cart !", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString()) }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    var sharedFile = "id.ac.ubaya.infor.shoppa"
                    var shared: SharedPreferences = fragment.context!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                    params["id_user"] = shared.getInt(USER_ID, 0).toString()
                    params["id_prod"] = products[position].id.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }

        holder.v.btnDetail.setOnClickListener {
            val intent = Intent(this.fragment.context, DetailActivity::class.java)
            intent.putExtra(PRODUCT_ID, products[position].id.toString())
            this.fragment.startActivity(intent)
//            Toast.makeText(this.fragment.context, "This is button Detail", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }
}