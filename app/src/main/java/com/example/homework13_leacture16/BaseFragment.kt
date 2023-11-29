package com.example.homework13_leacture16

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflater<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB  // lambda function

abstract class BaseFragment<VB: ViewBinding>(private val inflate : Inflater<VB>): Fragment() {

    private var _binding: VB? = null // This property is only valid between onCreateView and onDestroyView.
    val binding: VB get() = _binding!! // binding will always get the value from _binding each time it gets accessed, so same for this too
    // binding doesn't hold any referance to _binding, each time we access binding it retreavs value from _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("tag123","FragmentOnCreate")
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("tag123","FragmentOnCreateView")
        _binding = inflate.invoke(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("tag123","FragmentOnViewCreated")
        setUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun setUp()
    abstract fun initData()
}