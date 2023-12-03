package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoplist.domain.ShopItem

@Dao
interface ShopItemDao {
    @Insert
    fun addShopItem(item: ShopItem)

    @Update
    fun editShopItem(item: ShopItem)

    @Query("SELECT * FROM item_table WHERE id_item==:id LIMIT 1")
    fun getItem(id: Long) : ShopItemEntity

    @Query("SELECT * FROM item_table ORDER BY id_item ASC")
    fun getItems(): LiveData<List<ShopItemEntity>>
}