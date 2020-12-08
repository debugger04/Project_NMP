package id.ac.ubaya.infor.shoppa

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_home.view.*
import java.text.NumberFormat

class HomeProductAdapter(var products: ArrayList<Product>, val fragment: HomeFragment):
    RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder>() {
    class HomeProductViewHolder(var v: View, val fragment: HomeFragment):RecyclerView.ViewHolder(v)

    val PRODUCT_ID = ""

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
            Toast.makeText(this.fragment.context, "This is button add to cart", Toast.LENGTH_SHORT).show()
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