package com.example.shoplist.presentation

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplist.data.ShopItemRepositoryImpl
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecase.AddShopItem
import com.example.shoplist.domain.usecase.EditShopItem
import com.example.shoplist.domain.usecase.GetShopItem

class ShopItemViewModel: ViewModel() {
    private val repository = ShopItemRepositoryImpl

    private val editShopItemUseCase = EditShopItem(repository)
    private val addShopItemUseCase = AddShopItem(repository)
    private val getShopItemUseCase = GetShopItem(repository)

    private val _itemLiveData = MutableLiveData<ShopItem>()
    val itemLiveData: LiveData<ShopItem>
        get() = _itemLiveData

    private var _errorNameLD = MutableLiveData<Boolean>()
        val errorNameLD: LiveData<Boolean>
            get() = _errorNameLD

    private var _errorCountLD = MutableLiveData<Boolean>()
        val errorCountLD: LiveData<Boolean>
            get() = _errorCountLD

    private var _finishActivityLD = MutableLiveData<Unit>()
    val finishActivityLD: LiveData<Unit>
        get() = _finishActivityLD

    private fun parseInputName(inputName: Editable?) = inputName?.toString() ?: ""
    private fun parseInputCount(inputCount: Editable?): Int {
//        Log.d("XXXXX", "parseinputCount $inputCount")
        if (inputCount.toString() == "") return 0
        val res = inputCount?.toString()?.toInt() ?: 0
//        Log.d("XXXXX", "parseinputCount $res")
        return res
    }

    private fun validateData(name: String, count: Int): Boolean {
//        Log.d(AddItemActivity.TAG, "validateData: $name, $count")
        var res = true
        if (name == "") {
            res = false
            _errorNameLD.value = true
        }
        if (count <= 0) {
            res = false
            _errorCountLD.value = true
        }
        return res
    }

    fun getItem(id: Long) {
        val shopItem = getShopItemUseCase.getItem(id)
        _itemLiveData.value = shopItem
    }

    fun addItem(inputName: Editable?, inputCount: Editable?) {

        val name = parseInputName(inputName)
//        Log.d("XXXXX","After parseInputName")
        val count = parseInputCount(inputCount)
//        Log.d("XXXXX","After parseInputCount")

//        Log.d(AddItemActivity.TAG, "ShopItemViewModel (before validate) addItem: $name, $count")

        if (validateData(name, count)) {
//            Log.d(AddItemActivity.TAG, "ShopItemViewModel addItem: $name, $count")
            val item = ShopItem(name, count)
            addShopItemUseCase.addItem(item)
            _finishActivityLD.value = Unit
        }
    }
    fun editItem(inputName: Editable?, inputCount: Editable?) {
        val item = _itemLiveData.value

        val name = parseInputName(inputName)
        val count = parseInputCount(inputCount)

        if (validateData(name, count)) {
            item?.let {
                val itemShop = it.copy(name = name, count = count)
                editShopItemUseCase.editItem(itemShop)
                _finishActivityLD.value = Unit
            }
        }
    }

}
