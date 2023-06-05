package id.capstone.wawasan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.capstone.wawasan.databinding.ItemDummyBinding
import id.capstone.wawasan.retrofit.DataItem

class DataAdapter(private val list: List<DataItem>) : RecyclerView.Adapter<DataAdapter.DummyViewHolder>() {
    class DummyViewHolder(private val binding: ItemDummyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dummy: DataItem) {
            with(binding) {
                tvSales.text = dummy.supplier
                tvProduct.text = dummy.nama
                tvStock.text = dummy.toko
                tvRcstock.text = dummy.gudang
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        val binding = ItemDummyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DummyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        holder.bind(list[position])
    }
}