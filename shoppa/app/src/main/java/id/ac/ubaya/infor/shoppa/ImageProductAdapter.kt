package id.ac.ubaya.infor.shoppa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_product_slider_home.view.*

class ImageProductAdapter(var images: ArrayList<ImageProduct>, var fragment: HomeFragment) : RecyclerView.Adapter<ImageProductAdapter.TemplateViewHolder>() {
    class TemplateViewHolder(var v: View, var fragment:HomeFragment): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :TemplateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_product_slider_home, parent,false)
        return TemplateViewHolder(v, fragment)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
//        holder.v.txtTemplate.text = templates[position]
//        holder.v.btnPick.setOnClickListener {
//            val intent = Intent()
//            intent.putExtra(TemplateMessageActivity.TEMPLATE_MESSAGE, 						templates[position])
//            holder.activity.setResult(Activity.RESULT_OK, intent)
//            holder.activity.finish()
//        }
        holder.v.textView11.text = images[position].img
    }

}