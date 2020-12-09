package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.layout_product_cart.view.*
import org.json.JSONObject
import java.text.NumberFormat

class CartProductAdapter(var products: ArrayList<Product>, val fragment: CartFragment):
    RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>() {
    class CartProductViewHolder(var v: View, val fragment: CartFragment):RecyclerView.ViewHolder(v)
    val USER_ID = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_cart, parent,false)
        return CartProductAdapter.CartProductViewHolder(v, fragment)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val url = products[position].image
        Picasso.get().load(url).into(holder.v.imgCardView)
        holder.v.txtNamaBarang.text = products[position].nama
        holder.v.txtDeskripsi.text = products[position].deskripsi
        holder.v.txtJmlItem.text = products[position].qty.toString()
        holder.v.txtHargaAsli.text = "Rp " + NumberFormat.getInstance().format(products[position].harga)
        var subtotal = products[position].qty * products[position].harga
        holder.v.txtSubtotal.text = "Rp " + NumberFormat.getInstance().format(subtotal)

        holder.v.btnPlusItem.setOnClickListener {
            val q = Volley.newRequestQueue(fragment.context)
            val url = "http://10.0.2.2/nmp160418083/cart_add_product.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        products[position].qty++
                        holder.v.txtJmlItem.text = products[position].qty.toString()
                        var subtotal = products[position].qty * products[position].harga
                        holder.v.txtSubtotal.text = "Rp " + NumberFormat.getInstance().format(subtotal)
                        val data = obj.getJSONArray("data")
                        for(i in 0 until data.length()) {
                            val dataObj = data.getJSONObject(i)
                            fragment.jmlHarga.text = "Rp " + NumberFormat.getInstance().format(dataObj.getInt("grand_total"))
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
                    var shared: SharedPreferences = fragment.context!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                    params["id_user"] = shared.getInt(USER_ID, 0).toString()
                    params["id_prod"] = products[position].id.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }

        holder.v.btnMinusItem.setOnClickListener {
            val q = Volley.newRequestQueue(fragment.context)
            val url = "http://10.0.2.2/nmp160418083/cart_minus_product.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                Response.Listener {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        products[position].qty--
                        holder.v.txtJmlItem.text = products[position].qty.toString()
                        var subtotal = products[position].qty * products[position].harga
                        holder.v.txtSubtotal.text = "Rp " + NumberFormat.getInstance().format(subtotal)
                        val data = obj.getJSONArray("data")
                        for(i in 0 until data.length()) {
                            val dataObj = data.getJSONObject(i)
                            fragment.jmlHarga.text = "Rp " + NumberFormat.getInstance().format(dataObj.getInt("grand_total"))
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
                    var shared: SharedPreferences = fragment.context!!.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
                    params["id_user"] = shared.getInt(USER_ID, 0).toString()
                    params["id_prod"] = products[position].id.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}