package com.example.homework13_leacture16

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13_leacture16.databinding.FieldItemBinding
import com.example.homework13_leacture16.databinding.FieldStackItemBinding

class FieldStackAdapterRecycler(private val listener: ItemListener) : ListAdapter<FieldStack, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {

        private lateinit var myAdaper: FieldAdapterRecycler

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FieldStack>() {
            override fun areItemsTheSame(oldItem: FieldStack, newItem: FieldStack): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FieldStack, newItem: FieldStack): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class FieldViewHolder(private val binding: FieldStackItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(fieldStack: FieldStack){

            myAdaper = FieldAdapterRecycler()

            Log.d("tag123","bind() -> ${fieldStack.id} , ${fieldStack.list.size}")

            binding.root.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = myAdaper
            }

            myAdaper.submitList(fieldStack.list)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("tag123","onCreateViewHolder")
        return FieldViewHolder(
            FieldStackItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("tag123","onBindViewHolder")
        val fieldStack = getItem(position)
        if(holder is FieldViewHolder)holder.bind(fieldStack)
    }
}

interface ItemListener {
    fun onItemInput(fieldStack: FieldStack)
}