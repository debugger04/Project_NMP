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
import kotlinx.android.synthetic.main.layout_card_detail_history.view.*
import kotlinx.android.synthetic.main.layout_product_home.view.*
import org.json.JSONObject
import java.text.NumberFormat

class CategoryProductAdapter(val products:ArrayList<Product>, val ctx:Context)
    : RecyclerView.Adapter<CategoryProductAdapter.CategoryProductViewHolder>() {
    class CategoryProductViewHolder(val v: View, val ctx:Context):RecyclerView.ViewHolder(v)
    val USER_ID = ""
    val PRODUCT_ID = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_home, parent,false)
        return CategoryProductAdapter.CategoryProductViewHolder(v, ctx)
    }

    override fun onBindViewHolder(holder: CategoryProductViewHolder, position: Int) {
        val url = products[position].image
        Picasso.get().load(url).into(holder.v.imgView)
        holder.v.productName.text = products[position].nama
        holder.v.productDesc.text = products[position].deskripsi
        var price = NumberFormat.getInstance().format(products[position].harga)
        holder.v.productPrice.text = "Rp " + price

        holder.v.btnAddToCart.setOnClickListener {
            val q = Volley.newRequestQueue(ctx)
            val url = "http://10.0.2.2/nmp160418083/add_to_cart.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        Toast.makeText(ctx, "Item has been added to cart !", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Log.d("cekparams", it.message.toString()) }
            )
            {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    var sharedFile = "id.ac.ubaya.infor.shoppa"
                    var shared: SharedPreferences = ctx.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                    params["id_user"] = shared.getInt(USER_ID, 0).toString()
                    params["id_prod"] = products[position].id.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }

        holder.v.btnDetail.setOnClickListener {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra(PRODUCT_ID, products[position].id.toString())
            ctx.startActivity(intent)
//            Toast.makeText(this.fragment.context, "This is button Detail", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}