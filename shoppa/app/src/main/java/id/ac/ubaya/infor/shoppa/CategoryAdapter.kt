package id.ac.ubaya.infor.shoppa

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_slider_home.view.*

class CategoryAdapter(var categories: ArrayList<Category>, val fragment: HomeFragment):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(var v: View, val fragment: HomeFragment):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_slider_home, parent,false)
        return CategoryViewHolder(v, activity)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val url = categories[position].image
        Picasso.get().load(url).into(holder.v.imgView)
        holder.v.txtView.text = categories[position].nama
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}