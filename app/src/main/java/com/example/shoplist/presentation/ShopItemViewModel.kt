package com.example.shoplist.presentation

import android.text.Editable
import com.example.shoplist.data.ShopItemRepositoryImpl
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecase.AddShopItem
import com.example.shoplist.domain.usecase.EditShopItem
import com.example.shoplist.domain.usecase.GetShopItem

class ShopItemViewModel {
    private val repository = ShopItemRepositoryImpl

    private val editShopItemUseCase = EditShopItem(repository)
    private val addShopItemUseCase = AddShopItem(repository)
    private val getShopItemUseCase = GetShopItem(repository)



    fun getItem(id: Long) {

    }

    fun addItem(inputName: Editable?, inputCount: Editable?) {

    }

    fun editItem(inputName: Editable?, inputCount: Editable?) {

    }
}
