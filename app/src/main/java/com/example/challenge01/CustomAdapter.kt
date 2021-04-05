package com.example.challenge01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val target: MutableList<ItemType>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_view, parent, false
        ))
    }

    fun addToList(item: ItemType){
        target.add(item)
        notifyItemInserted(target.size - 1)
    }

    fun deleteCheckedItem(){
        target.removeAll{ item -> item.isChecked }
        notifyDataSetChanged()
    }

    fun getCheckedItem(): List<ItemType>{
        return target.filter{ it.isChecked }
    }

    override fun getItemCount(): Int {
        return target.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = target[position]
        holder.itemView.apply{
            findViewById<TextView>(R.id.tvItem).text = currentItem.item
            findViewById<CheckBox>(R.id.cbCheck).isChecked = currentItem.isChecked
            findViewById<CheckBox>(R.id.cbCheck).setOnCheckedChangeListener{ _, _ ->
                currentItem.isChecked = !currentItem.isChecked
            }
        }
    }
}