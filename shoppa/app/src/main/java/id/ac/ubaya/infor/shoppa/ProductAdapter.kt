package id.ac.ubaya.infor.shoppa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_home.view.*
import kotlinx.android.synthetic.main.layout_product_slider_home.view.*
import kotlinx.android.synthetic.main.layout_product_slider_home.view.imageCardView

class ProductAdapter(val images:ArrayList<ImageProduct>, val fragment: HomeFragment): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(val v: View, val fragment:HomeFragment): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_home, parent,false)
        return ProductViewHolder(v, fragment)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val url = images[position].img
        Picasso.get().load(url).into(holder.v.imageCardView)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}