package com.example.listapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.databinding.ListSelectionViewHolderBinding
import com.example.listapp.models.TaskList

class ListSelectionRecyclerViewAdapter(
    val lists: MutableList<TaskList>,
    val clickListener: ListSelectionRecyclerViewClickListener
) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {


    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

        /*LayoutInflater adalah utilitas sistem yang
        digunakan untuk membuat instance (atau "mengembang")
        file XML tata letak ke dalam objek View yang sesuai.*/
        val binding = ListSelectionViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemString.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun listUpdated() {
        notifyItemInserted(lists.size - 1)
    }
}