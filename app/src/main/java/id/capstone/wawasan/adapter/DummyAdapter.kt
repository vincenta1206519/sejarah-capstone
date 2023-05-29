package id.capstone.wawasan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.capstone.wawasan.databinding.ItemDummyBinding

//class DummyAdapter(private val list: ArrayList<Dummy>) : RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {
//    class DummyViewHolder(private val binding: ItemDummyBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(dummy: Dummy) {
//            with(binding) {
//                tvSales.text = dummy.sales
//                tvProduct.text = dummy.product
//                tvStock.text = dummy.stock
//                tvRcstock.text = dummy.recStock
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
//        val binding = ItemDummyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DummyViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = list.size
//
//    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
//        holder.bind(list[position])
//    }
//}