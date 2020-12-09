package id.ac.ubaya.infor.shoppa

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_card_history.view.*
import java.text.NumberFormat

class HistoryAdapter(var transactions: ArrayList<Transaction>, val fragment: HistoryFragment):
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(var v: View, val fragment: HistoryFragment):RecyclerView.ViewHolder(v)
    val NO_INVOICE = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.layout_card_history, parent,false)
        return HistoryAdapter.HistoryViewHolder(v, fragment)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.v.history_invoice.text = transactions[position].no_invoice
        if (transactions[position].tipe == "pay") {
            holder.v.history_type.text = "PAID"
            holder.v.history_grandTot.setTextColor(Color.RED)
            holder.v.history_grandTot.text = "(-) Rp " + NumberFormat.getInstance().format(transactions[position].grandtot)
        } else {
            holder.v.history_type.text = "TOP UP"
            holder.v.history_grandTot.setTextColor(Color.GREEN)
            holder.v.history_grandTot.text = "(+) Rp " + NumberFormat.getInstance().format(transactions[position].grandtot)
            holder.v.btnViewDetailOrder.visibility = View.INVISIBLE
        }
        holder.v.history_date.text = transactions[position].tanggal.substring(0,10)

        holder.v.btnViewDetailOrder.setOnClickListener {
            val intent = Intent(this.fragment.context, DetailHistoryActivity::class.java)
            intent.putExtra(NO_INVOICE, transactions[position].no_invoice)
            this.fragment.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}