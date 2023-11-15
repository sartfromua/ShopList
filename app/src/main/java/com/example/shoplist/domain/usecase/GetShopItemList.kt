package com.example.shoplist.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItemRepository

public class GetShopItemList (
    private val repository: ShopItemRepository
) {
    fun getItemList():LiveData<List<ShopItem>>
    {
        return repository.getItemList()
    }
}
