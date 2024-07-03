package com.sonia.recyclerviewadapter

import android.media.RouteListingPreference.Item
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var counter:Int) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    // private val TAG = "RecyclerAdapter"

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        // vartv name: TextView = view.findViewById(R.id.tvname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return counter
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  Log.e(TAG, "onBindViewHolder: position $position",)
        // holder.tvname.setText(position.toString())
    }

    fun addData(newData: Int) {
        counter = newData
        notifyDataSetChanged()
    }
}