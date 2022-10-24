package com.example.lab2.ui.secondActivity


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val index : LiveData<Int> = _index
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}