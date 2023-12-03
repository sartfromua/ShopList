package com.example.shoplist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoplist.presentation.UNDEFINED_ID

@Entity(tableName = "item_table")
data class ShopItemEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_item")
    var id: Long = UNDEFINED_ID,

    @ColumnInfo(name = "name_item")
    val name: String,

    @ColumnInfo(name = "count_item")
    val count: Int,

    @ColumnInfo(name = "active_item")
    val active: Boolean = true,
)