package com.example.homework13_leacture16.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.homework13_leacture16.data.Field
import com.example.homework13_leacture16.data.FieldStack

class InputFragmentViewModel :ViewModel(){

    init {
        Log.d("tag123","ViewModel Init")
    }

    // we gonna need it for checking the input
    var fieldsSize = 0
    var fieldsIdList = mutableListOf<Int>()
    var fieldIdFieldMap = mutableMapOf<Int, Field>()
    var fieldIdInputMap = mutableMapOf<Int,String>()

    //if some required field isn't field I will save its hint here
    var failedRequiredField = ""

    val list = listOf(
        FieldStack(1, listOf(Field(1,"name","input","",true,false,""))),
        FieldStack(2, listOf(Field(2,"lastName","input","",false,false,""))),
        FieldStack(3, listOf(
            Field(3,"age","input","",true,false,""),
            Field(4,"address","input","",false,false,""),
            Field(5,"sasdff","chooser","",false,false,"")
        ))
    )

    fun validateData():Boolean{
        //Log.d("tag1234","checking!!!")
        var ans = false
        for(id in fieldsIdList){
            if(fieldIdFieldMap[id]!!.required){
                ans = ans.or( (fieldIdInputMap[id]!!.isEmpty()) )
                if(fieldIdInputMap[id]!!.isEmpty()){
                    failedRequiredField = fieldIdFieldMap[id]!!.hint
                    break
                }
                //Log.d("tag1234","checked $it ${fieldsIdList.size}")
            }
        }
        return ans
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("tag123","ViewModel onClear")
    }

    fun addField(field:Field){
        if(!fieldsIdList.contains(field.field_id)){
            fieldsIdList.add(field.field_id)
            fieldIdFieldMap[field.field_id] = field
            fieldIdInputMap[field.field_id] = ""
            fieldsSize++
        }
    }

}