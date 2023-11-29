package com.example.homework13_leacture16

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object JsonUtils {
    fun readFieldsFromJson(context: Context): List<Field> {
        val json = try {
            context.assets.open("data.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listType = object : TypeToken<List<Field>>() {}.type
        return Gson().fromJson(json, listType)
    }
}