package com.virakumaro.testixid.base

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseVBActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB

    protected abstract fun getViewBinding(): VB
    protected abstract fun initData(savedInstanceState: Bundle?)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initData(savedInstanceState)
    }

}