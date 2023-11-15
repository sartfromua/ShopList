package com.example.shoplist.domain.usecase

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItemRepository

class EditShopItem (
    private val repository: ShopItemRepository
) {
    fun editItem(item: ShopItem) {
        repository.editItem(item)
    }
}