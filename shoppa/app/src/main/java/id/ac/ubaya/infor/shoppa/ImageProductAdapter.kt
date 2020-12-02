package id.ac.ubaya.infor.shoppa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_slider_home.view.*

class ImageProductAdapter(val images:ArrayList<ImageProduct>):RecyclerView.Adapter<ImageProductAdapter.ImageProductViewHolder>() {
    class ImageProductViewHolder(val v:View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_slider_home, parent,false)
        return ImageProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ImageProductViewHolder, position: Int) {
        val url = images[position].img
        Picasso.get().load(url).into(holder.v.imageProductView)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}