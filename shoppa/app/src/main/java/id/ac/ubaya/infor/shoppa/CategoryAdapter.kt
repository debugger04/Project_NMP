package id.ac.ubaya.infor.shoppa

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_product_slider_home.view.*

class CategoryAdapter(var categories: ArrayList<Category>, val fragment: HomeFragment):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(var v: View, val fragment: HomeFragment):RecyclerView.ViewHolder(v)
    val CATEGORY_ID = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_slider_home, parent,false)
        return CategoryViewHolder(v, fragment)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val url = categories[position].image
        Picasso.get().load(url).into(holder.v.imgView)
        holder.v.btnCategory.text = categories[position].nama

        holder.v.imgView.setOnClickListener {
            val intent = Intent(this.fragment.context, CategoryProductActivity::class.java)
            intent.putExtra(CATEGORY_ID, categories[position].id.toString())
            this.fragment.startActivity(intent)
//            Toast.makeText(this.fragment.context, "This is Category Card", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}