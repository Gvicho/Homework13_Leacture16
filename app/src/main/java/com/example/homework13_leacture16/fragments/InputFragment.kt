package com.example.homework13_leacture16.fragments

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13_leacture16.ViewModel.InputFragmentViewModel
import com.example.homework13_leacture16.adapters.FieldStackAdapterRecycler
import com.example.homework13_leacture16.adapters.ItemListener
import com.example.homework13_leacture16.base_fragments.BaseFragment
import com.example.homework13_leacture16.data.Field
import com.example.homework13_leacture16.data.FieldStack
import com.example.homework13_leacture16.databinding.FragmentInputBinding


class InputFragment : BaseFragment<FragmentInputBinding>(FragmentInputBinding::inflate),
    ItemListener {

    private lateinit var myAdaper: FieldStackAdapterRecycler

    private val viewModel:InputFragmentViewModel by viewModels()


    companion object {

        @JvmStatic
        fun newInstance() =
            InputFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    init {
        Log.d("tag123","Fragment Init")
    }



    override fun setUp() {
        Log.d("tag123","SetUp")

        myAdaper = FieldStackAdapterRecycler(this)

        bindingApplyChanges(viewModel.list)

    }

    private fun bindingApplyChanges(list: List<FieldStack>){

        binding.apply {

            recyclerMain.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
                adapter = myAdaper

            }

            myAdaper.submitList(list)

            registrationButton1.setOnClickListener{
                registrate()
            }

            registrationButton.setOnClickListener{
                registrate()
            }
        }
    }

    private fun registrate(){
        if(!viewModel.validateData()){
            Toast.makeText(this@InputFragment.context, "Info is saved ", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@InputFragment.context, "${viewModel.failedRequiredField} is required field, please fill it", Toast.LENGTH_SHORT).show()
        }
    }



    override fun initData() {

    }

    override fun onItemInput(field: Field) {
        addField(field)
    }

    override fun onItemInputChanged(input: String, field: Field) {
        viewModel.fieldIdInputMap[field.field_id] = input
        //Log.d("tag1234","changed  ${field.field_id} size is : ${viewModel.fieldIdInputMap[field.field_id]}")
    }

    private fun addField(field: Field){
        //Log.d("tag1234","added  ${field.field_id} size is : ${viewModel.fieldsSize}")
        viewModel.addField(field)

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("tag123","onDetach()")
    }


}