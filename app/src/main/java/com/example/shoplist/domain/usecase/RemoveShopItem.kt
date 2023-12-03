package com.example.shoplist.domain.usecase

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItemRepository

class RemoveShopItem (
    private val repository: ShopItemRepository
) {
    suspend fun removeItem(item: ShopItem) {
        repository.removeItem(item)
    }
}