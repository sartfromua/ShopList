package com.example.shoplist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val isActive: Boolean = true,
    var id: Long = UNDEFINED

) {
    companion object {
        const val UNDEFINED = -1L
    }

    override fun toString(): String {
        return "$id: $name - $count ($isActive)"
    }
}