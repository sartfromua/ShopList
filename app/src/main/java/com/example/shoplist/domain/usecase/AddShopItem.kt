package com.example.shoplist.domain.usecase

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItemRepository

class AddShopItem (
    private val repository: ShopItemRepository
) {
    suspend fun addItem(item: ShopItem) {
        repository.addItem(item)
    }
}