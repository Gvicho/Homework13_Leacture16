package com.example.homework13_leacture16

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13_leacture16.databinding.FieldItem1Binding
import com.example.homework13_leacture16.databinding.FieldItemBinding
import com.example.homework13_leacture16.databinding.FieldStackItemBinding


class FieldAdapterRecycler() : ListAdapter<Field, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    companion object {

        const val Item_Type_1 = 1
        const val Item_Type_2 = 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Field>() {
            override fun areItemsTheSame(oldItem: Field, newItem: Field): Boolean {
                return oldItem.field_id == newItem.field_id
            }

            override fun areContentsTheSame(oldItem: Field, newItem: Field): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class FieldViewHolder(private val binding: FieldItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(field: Field, position: Int){
            binding.root.hint = field.hint
            if(position == currentList.size-1){
                binding.root.background = null
            }
        }
    }

    inner class FieldViewHolder1(private val binding: FieldItem1Binding): RecyclerView.ViewHolder(binding.root){

        fun bind(field: Field, position: Int){

        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when(item.field_type){
            "input" -> Item_Type_1
            else -> Item_Type_2
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if(viewType == Item_Type_1){

            return FieldViewHolder(
                FieldItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )

        }else{

            return FieldViewHolder1(
                FieldItem1Binding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val field= getItem(position)
        if(holder is FieldViewHolder)holder.bind(field,position)
        else if (holder is FieldViewHolder1)holder.bind(field,position)
    }
}

interface ItemListener1 {
    fun onItemInput(field: Field)
}