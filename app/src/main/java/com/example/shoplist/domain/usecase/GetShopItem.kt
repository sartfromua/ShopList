package com.example.shoplist.domain.usecase

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItemRepository

class GetShopItem (
    private val repository: ShopItemRepository
    ) {
    suspend fun getItem(id: Long): ShopItem {
            return repository.getItem(id)
        }
    }