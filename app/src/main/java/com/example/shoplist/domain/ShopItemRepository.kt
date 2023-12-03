package com.example.shoplist.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface ShopItemRepository {
    suspend fun addItem(item: ShopItem)
    suspend fun editItem(item: ShopItem)
    suspend fun getItem(id: Long): ShopItem
    fun getItemList(): LiveData<List<ShopItem>>
    suspend fun removeItem(item: ShopItem)

}