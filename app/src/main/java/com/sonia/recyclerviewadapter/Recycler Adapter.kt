package com.sonia.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var factList : ArrayList<Facts>,var recyclerInterface: RecyclerInterface):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var tvtitle: TextView = view.findViewById(R.id.title)
        var tvdecription: TextView = view.findViewById(R.id.description)
        var btndelete: ImageButton = view.findViewById(R.id.btndelete)
        var btnupdate: ImageButton = view.findViewById(R.id.btnupdate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return factList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvtitle.setText(factList[position].title.toString())
        holder.tvdecription.setText(factList[position].description.toString())
        holder.btndelete.setOnClickListener {
            recyclerInterface.delete(position)
        }
        holder.btnupdate.setOnClickListener {
            recyclerInterface.update(position)
        }
    }
}