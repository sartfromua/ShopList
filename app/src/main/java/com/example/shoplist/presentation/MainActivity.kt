package com.example.shoplist.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.databinding.ActivityMainBinding
import com.example.shoplist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopItemAdapter

    lateinit var binding: ActivityMainBinding

    val isPortrait: Boolean
        get() = binding.shopItemFragmentContainerLandscape == null
    private fun setupFragment(fragment: ShopItemFragment) {
//        Log.d("XXXXX", "fragment args: ${fragment.arguments.toString()}")
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.shop_item_fragment_container_landscape, fragment)
            .commit()
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveData.observe(this) {
            Log.d(TAG, "onCreate: ${it.toList()}" )
            adapter.shopItems = it
        }

        Log.d(TAG, "getShopItemList: ${viewModel.getShopItemList().value}" )


        adapter = ShopItemAdapter()
        binding.recyclerView.adapter = adapter
        adapter.clickListener = { view: View, item: ShopItem ->
            Toast.makeText(this@MainActivity, item.toString(), Toast.LENGTH_SHORT).show()
        }

        adapter.clickListener = { view: View, item: ShopItem->
            try {
                viewModel.toggleItemActivity(item) // FIXME
                Log.d(TAG, "Trying to toggle item correct")
            } catch (e: Exception) {
                Log.d(TAG, "Trying to toggle item FAILED: ${e.message.toString()}")
            }
        }

        adapter.swipeListener = {
            viewModel.removeShopItem(it)
        }
        val itemTouchHelper = ItemTouchHelper(adapter.simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        adapter.longClickListener = {view: View, item: ShopItem ->
            if (isPortrait) {
                lunchEditItemActivity(item.id)
            } else {
                setupFragment(ShopItemFragment.newInstanceFragmentEdit(item.id))
            }
        }

        binding.addItemButton.setOnClickListener {
            Log.d("XXXXX", "Lunching AddItemActivity")
            if (isPortrait) {
            lunchAddItemActivity()
            } else {
                setupFragment(ShopItemFragment.newInstanceFragmentAdd())
            }
        }


    }
    private fun lunchAddItemActivity() {
        val intent = Intent(this, AddItemActivity::class.java)
        intent.putExtra(EXTRA_MODE, ADD_ITEM_MODE)
        startActivity(intent)
    }

    private fun lunchEditItemActivity(itemId: Long) {
        val intent = Intent(this, AddItemActivity::class.java)
        intent.putExtra(EXTRA_MODE, EDIT_ITEM_MODE)
        intent.putExtra(EXTRA_ITEM_ID, itemId)
        startActivity(intent)
    }

    companion object {
        const val TAG = "XXXXX"
    }
}