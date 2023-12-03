package com.example.shoplist.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shoplist.data.DataBaseRepository
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.usecase.EditShopItem
import com.example.shoplist.domain.usecase.GetShopItemList
import com.example.shoplist.domain.usecase.RemoveShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = DataBaseRepository(application)

    private val getShopItemListUseCase = GetShopItemList(repository)
    private val editShopItemUseCase = EditShopItem(repository)
    private val removeShopItemListUseCase = RemoveShopItem(repository)

    val liveData: LiveData<List<ShopItem>>
        //get() = repository.liveData
        get() = getShopItemListUseCase.getItemList()

    fun getShopItemList(): LiveData<List<ShopItem>> {
        return getShopItemListUseCase.getItemList()
    }

    fun toggleItemActivity(item: ShopItem) {
        viewModelScope.launch {
            val newItem = item.copy(isActive = !item.isActive)
            editShopItemUseCase.editItem(newItem)
        }
    }

    fun removeShopItem(item: ShopItem) {
        viewModelScope.launch {
            removeShopItemListUseCase.removeItem(item)
        }
    }
}