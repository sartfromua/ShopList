package com.example.shoplist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [ShopItemEntity::class], version = 1)
abstract class ShopItemDB: RoomDatabase() {

    abstract fun getDao() : ShopItemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ShopItemDB? = null

        fun getInstance(context: Context): ShopItemDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let { return it }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopItemDB::class.java,
                    "word_database"
                )
//                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}