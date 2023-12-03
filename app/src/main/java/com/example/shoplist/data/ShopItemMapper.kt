package com.example.shoplist.data

import com.example.shoplist.domain.ShopItem

object ShopItemMapper {
    fun entityToShopItem(entity: ShopItemEntity): ShopItem {
        return ShopItem(
            name = entity.name,
            count = entity.count,
            isActive = entity.active,
            id = entity.id
        )
    }

    fun shopItemToEntity(item: ShopItem) : ShopItemEntity {
        return ShopItemEntity(
            name = item.name,
            count = item.count,
            active = item.isActive,
            id = item.id
        )
    }

    fun entitiesToShopItem(list: List<ShopItemEntity>): List<ShopItem> {
        return list.map { entityToShopItem(it)}
    }
}