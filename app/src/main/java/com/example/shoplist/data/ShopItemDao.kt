package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShopItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(item: ShopItemEntity)

    @Update
    suspend fun editShopItem(item: ShopItemEntity)

    @Query("SELECT * FROM item_table WHERE id_item==:id LIMIT 1")
    suspend fun getItem(id: Long) : ShopItemEntity

    @Query("SELECT * FROM item_table ORDER BY id_item ASC")
    fun getItems(): LiveData<List<ShopItemEntity>>

    @Delete
    suspend fun removeShopItem(item: ShopItemEntity)
}