package com.example.homework13_leacture16

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13_leacture16.databinding.FragmentInputBinding


class InputFragment : BaseFragment<FragmentInputBinding>(FragmentInputBinding::inflate),ItemListener {

    private lateinit var myAdaper: FieldStackAdapterRecycler

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
            FieldStack(1, listOf(Field(1,"name","input","",false,false,""))),
            FieldStack(2, listOf(Field(2,"d","input","",false,false,""))),
            FieldStack(3, listOf(Field(3,"s","input","",false,false,""),
                Field(4,"fd","input","",false,false,""),
                Field(5,"sasdff","chooser","",false,false,"")))
        )

        /*
        json input
         */


        binding.recyclerMain.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            adapter = myAdaper

        }
        myAdaper.submitList(list)
    }

    override fun initData() {

    }

    override fun onItemInput(fieldStack: FieldStack) {

    }


}