package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        if (savedInstanceState == null)
            parseIntent()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        Log.d("XXXXX","Before parseIntent")
//        Log.d("XXXXX","After parseIntent")
//        registerLiveData()
    }

    private fun setupFragment(fragment: ShopItemFragment) {
//        Log.d("XXXXX", "fragment args: ${fragment.arguments.toString()}")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.shop_item_fragment_container, fragment)
            .commit()
    }



    private var mode: String = UNDEFINED_MODE
    private var itemId: Long = UNDEFINED_ID
    private fun parseIntent() {
        if (intent.hasExtra(EXTRA_MODE)) {
            mode = intent.getStringExtra(EXTRA_MODE) ?: UNDEFINED_MODE
//            Log.d(TAG, "parseItem: mode: $mode")
            when (mode) {
                ADD_ITEM_MODE -> {
//                    Log.d("XXXXX", "Intent: ADD_ITEM_MODE")
                    setupFragment(ShopItemFragment.newInstanceFragmentAdd())
                }
                EDIT_ITEM_MODE -> {
                    itemId = intent.getLongExtra(EXTRA_ITEM_ID, UNDEFINED_ID)
                    setupFragment(ShopItemFragment.newInstanceFragmentEdit(itemId))
                }
                else -> throw IllegalArgumentException("Wrong MODE in intent AddItemActivity!")
            }
        } else throw IllegalArgumentException("No MODE in intent AddItemActivity!")
    }



    companion object {
        val TAG = "XXXXX"
    }


}