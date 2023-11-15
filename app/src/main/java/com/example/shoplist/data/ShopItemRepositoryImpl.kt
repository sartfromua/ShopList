package com.example.shoplist.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopItem.Companion.UNDEFINED
import com.example.shoplist.domain.ShopItemRepository
import java.lang.RuntimeException

object ShopItemRepositoryImpl: ShopItemRepository {

    //    private val shopItems = sortedSetOf<ShopItem>({i1, i2 ->
//        i1.id.compareTo(i2.id)
//    }).toMutableSet()
    private val shopItems = sortedSetOf<ShopItem>(compareBy{it.id})
    private var currId = 0L
    val liveData = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 1..15) {
            addItem(
                ShopItem(
                    "Item_$i",
                    (i+1)%5,
                    true
                )
            )
        }
    }

    override fun addItem(item: ShopItem) {
        if (shopItems == null) {
            Log.e("XXXXXError", "shopItems is null when adding item.")
            return
        }
        if (item.id == UNDEFINED) item.id = currId++
        shopItems.add(item)

        update()
    }

    override fun editItem(item: ShopItem) {
        removeItem(getItem(item.id))
        addItem(item) // has update()
    }

    override fun getItem(id: Long): ShopItem {
        return shopItems.find {it.id == id}
            ?: throw  RuntimeException("Item not found!")
    }

    override fun getItemList(): LiveData<List<ShopItem>> {
        return liveData
    }

    override fun removeItem(item: ShopItem) {
        shopItems.remove(item)

        update()
    }

    private fun update() {
        liveData.value = shopItems.toList()
    }
}