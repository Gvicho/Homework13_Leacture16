package com.example.homework13_leacture16.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13_leacture16.data.Field
import com.example.homework13_leacture16.databinding.FieldItem1Binding
import com.example.homework13_leacture16.databinding.FieldItemBinding


class FieldAdapterRecycler(private val listener: ItemListener) : ListAdapter<Field, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
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

            listener.onItemInput(field)

            binding.apply {
                root.doOnTextChanged { text, start, before, count ->
                    listener.onItemInputChanged( root.text.toString(),field)
                }

                root.hint = field.hint

                //so that last field doesn't have a line under text
                if(position == currentList.size-1){
                    root.background = null
                }
            }



        }
    }

    inner class FieldViewHolder1(private val binding: FieldItem1Binding): RecyclerView.ViewHolder(binding.root){

        fun bind(field: Field){
            listener.onItemInput(field)
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
        else if (holder is FieldViewHolder1)holder.bind(field)
    }
}