package id.ac.ubaya.infor.shoppa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_cart.view.*
import java.text.NumberFormat

class CartProductAdapter(var products: ArrayList<Product>, val fragment: CartFragment):
    RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>() {
    class CartProductViewHolder(var v: View, val fragment: CartFragment):RecyclerView.ViewHolder(v)

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
        var subtotal = products[position].qty * products[position].harga
        holder.v.txtSubtotal.text = "Rp " + NumberFormat.getInstance().format(subtotal)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}