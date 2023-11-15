package com.example.shoplist.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface ShopItemRepository {
    fun addItem(item: ShopItem)
    fun editItem(item: ShopItem)
    fun getItem(id: Long): ShopItem
    fun getItemList(): LiveData<List<ShopItem>>
    fun removeItem(item: ShopItem)

}