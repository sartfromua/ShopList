package com.example.shoplist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplist.data.ShopItemRepositoryImpl
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecase.EditShopItem
import com.example.shoplist.domain.usecase.GetShopItemList
import com.example.shoplist.domain.usecase.RemoveShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopItemRepositoryImpl // FIXME: placeholder for demo

    private val getShopItemListUseCase = GetShopItemList(repository)
    private val editShopItemListUseCase = EditShopItem(repository)
    private val removeShopItemListUseCase = RemoveShopItem(repository)

    val liveData: LiveData<List<ShopItem>>
        //get() = repository.liveData
        get() = getShopItemListUseCase.getItemList()

    fun getShopItemList(): LiveData<List<ShopItem>> {
        return getShopItemListUseCase.getItemList()
    }

    fun toggleItemActivity(item: ShopItem) {
        Log.d("XXXXX", getShopItemList().value.toString())
        val newItem  = ShopItem(item.name, item.count, !item.isActive, item.id)
        Log.d("XXXXX", newItem.toString())
        editShopItemListUseCase.editItem(newItem)
        Log.d("XXXXX", getShopItemList().value.toString())
    }

    fun removeShopItem(item: ShopItem) {
        removeShopItemListUseCase.removeItem(item)
    }
}