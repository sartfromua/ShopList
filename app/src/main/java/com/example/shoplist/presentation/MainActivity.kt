package com.example.shoplist.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShopItemAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveData.observe(this) {
            Log.d(TAG, "onCreate: ${it.toList()}" )
            adapter.shopItems = it
        }

        Log.d(TAG, "getShopItemList: ${viewModel.getShopItemList().value}" )

        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = ShopItemAdapter()
        recyclerView.adapter = adapter
        adapter.clickListener = { view: View, item: ShopItem ->
            Toast.makeText(this@MainActivity, item.toString(), Toast.LENGTH_SHORT).show()
        }

        adapter.longClickListener = { view: View, item: ShopItem->
            try {
                viewModel.toggleItemActivity(item) // FIXME
                Log.d(TAG, "Trying to toggle item correct")
            } catch (e: Exception) {
                Log.d(TAG, "Trying to toggle item: ${e.message.toString()}")
            }
        }


    }

    companion object {
        const val TAG = "XXXXX"
    }
}