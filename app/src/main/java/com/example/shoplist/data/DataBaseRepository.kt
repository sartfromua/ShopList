package com.example.shoplist.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItemRepository

class DataBaseRepository(context: Context) : ShopItemRepository {
    private val dao = ShopItemDB.getInstance(context).getDao()
    override suspend fun addItem(item: ShopItem) {
        dao.addShopItem(ShopItemMapper.shopItemToEntity(item))
    }

    override suspend fun editItem(item: ShopItem) {
        dao.editShopItem(ShopItemMapper.shopItemToEntity(item))
    }

    override suspend fun getItem(id: Long): ShopItem {
        val entity = dao.getItem(id)
        return ShopItemMapper.entityToShopItem(entity)
    }

    override fun getItemList(): LiveData<List<ShopItem>> {
        val entities = dao.getItems()
        val liveData = MediatorLiveData<List<ShopItem>>()
        liveData.addSource(entities) {
            ShopItemMapper.entitiesToShopItem(it)
        }
        return liveData
    }

    override suspend fun removeItem(item: ShopItem) {
        dao.removeShopItem(ShopItemMapper.shopItemToEntity(item))
    }
}