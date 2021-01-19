package com.udacity.shoestore.models

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

//    private val _shoeCount = MutableLiveData<Int>()
//    val shoeCount: LiveData<Int>
//        get() = _shoeCount

    init {
        _shoes.value = mutableListOf()
    }
    fun onAdd(newShoe: Shoe?) {
        if (newShoe != null) {
            _shoes.value?.add(newShoe)
        }
    }



}

