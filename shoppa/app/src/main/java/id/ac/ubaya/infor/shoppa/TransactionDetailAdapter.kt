package id.ac.ubaya.infor.shoppa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_card_detail_history.view.*
import java.text.NumberFormat

class TransactionDetailAdapter(val details:ArrayList<TransactionDetail>)
    :RecyclerView.Adapter<TransactionDetailAdapter.TransactionDetailViewHolder>()  {
    class TransactionDetailViewHolder(val v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_card_detail_history, parent,false)
        return TransactionDetailViewHolder(v)
    }

    override fun onBindViewHolder(holder: TransactionDetailViewHolder, position: Int) {
        val url = details[position].imgBarang
        Picasso.get().load(url).into(holder.v.imgCardView)
        holder.v.txtJmlItem.text = "x" + details[position].qtyBarang
        holder.v.txtDeskripsi.text = "Rp " + NumberFormat.getInstance().format(details[position].hrgBarang)
        holder.v.txtNamaBarang.text = details[position].namaBarang
        var subtot = details[position].qtyBarang * details[position].hrgBarang
        holder.v.txtSubtotal.text = "Rp " + NumberFormat.getInstance().format(subtot)
    }

    override fun getItemCount(): Int {
        return details.size
    }
}