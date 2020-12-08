package id.ac.ubaya.infor.shoppa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_home.view.*
import java.text.NumberFormat

class HomeProductAdapter(var products: ArrayList<Product>, val fragment: HomeFragment):
    RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder>() {
    class HomeProductViewHolder(var v: View, val fragment: HomeFragment):RecyclerView.ViewHolder(v)

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
    }

    override fun getItemCount(): Int {
        return products.size
    }
}