package com.example.homework13_leacture16.fragments

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13_leacture16.adapters.FieldStackAdapterRecycler
import com.example.homework13_leacture16.adapters.ItemListener
import com.example.homework13_leacture16.base_fragments.BaseFragment
import com.example.homework13_leacture16.data.Field
import com.example.homework13_leacture16.data.FieldStack
import com.example.homework13_leacture16.databinding.FragmentInputBinding


class InputFragment : BaseFragment<FragmentInputBinding>(FragmentInputBinding::inflate),
    ItemListener {

    private lateinit var myAdaper: FieldStackAdapterRecycler

    // we gonna need it for checking the input
    private var fieldsSize = 0
    private var fieldsIdList = mutableListOf<Int>()
    private var fieldIdFieldMap = mutableMapOf<Int, Field>()
    private var fieldIdInputMap = mutableMapOf<Int,String>()

    companion object {

        @JvmStatic
        fun newInstance() =
            InputFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun setUp() {
        Log.d("tag123","SetUp")

        myAdaper = FieldStackAdapterRecycler(this)

        val list = listOf(
            FieldStack(1, listOf(Field(1,"name","input","",true,false,""))),
            FieldStack(2, listOf(Field(2,"d","input","",false,false,""))),
            FieldStack(3, listOf(
                Field(3,"s","input","",true,false,""),
                Field(4,"fd","input","",false,false,""),
                Field(5,"sasdff","chooser","",false,false,"")
            ))
        )

        binding.recyclerMain.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            adapter = myAdaper

        }

        myAdaper.submitList(list)

        binding.registrationButton1.setOnClickListener{
            if(!validateData()){
                Toast.makeText(this.context, "saved succesfuly", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registrationButton.setOnClickListener{
            if(!validateData()){
                Toast.makeText(this.context, "saved succesfuly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateData():Boolean{
        //Log.d("tag1234","checking!!!")
        var ans = false
        fieldsIdList.forEach {
            if(fieldIdFieldMap[it]!!.required){
                ans = ans.or( (fieldIdInputMap[it]!!.isEmpty()) )
                if(fieldIdInputMap[it]!!.isEmpty()){
                    Toast.makeText(this.context, "${fieldIdFieldMap[it]!!.hint} field is required", Toast.LENGTH_SHORT).show()
                }
                //Log.d("tag1234","checked $it ${fieldsIdList.size}")
            }
        }
        return ans
    }

    override fun initData() {

    }

    override fun onItemInput(field: Field) {
        addField(field)
    }

    override fun onItemInputChanged(input: String, field: Field) {
        fieldIdInputMap[field.field_id] = input
        Log.d("tag1234","changed  ${field.field_id} size is : ${fieldIdInputMap[field.field_id]}")
    }

    private fun addField(field: Field){
        Log.d("tag1234","added  ${field.field_id} size is : ${fieldsSize}")
        fieldsIdList.add(field.field_id)
        fieldIdFieldMap[field.field_id] = field
        fieldIdInputMap[field.field_id] = ""
        fieldsSize++
    }


}