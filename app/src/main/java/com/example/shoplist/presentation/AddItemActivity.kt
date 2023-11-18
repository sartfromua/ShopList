package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.R
import com.google.android.material.textfield.TextInputEditText

class AddItemActivity : AppCompatActivity() {

//    lateinit var inputNameLayout: TextInputLayout
//    lateinit var inputCountLayout: TextInputLayout

    lateinit var editName: TextInputEditText
    lateinit var editCount: TextInputEditText

    lateinit var saveButton: Button

    lateinit var viewModel: ShopItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]

        initViews()
        Log.d("XXXXX","Before parseIntent")
        parseIntent()
        Log.d("XXXXX","After parseIntent")
        registerLiveData()
    }

    private fun initViews() {
//        inputNameLayout = findViewById(R.id.input_name)
//        inputCountLayout = findViewById(R.id.input_count)

        editName = findViewById(R.id.edit_text_name)
//        editName.addTextChangedListener {
//            inputNameLayout.error = null
//        }
        editCount = findViewById(R.id.edit_text_count)
//        editCount.addTextChangedListener {
//            inputCountLayout.error = null
//        }

        saveButton = findViewById(R.id.buttonSave)

    }

    private fun lunchActivityForAdd() {
        saveButton.setOnClickListener {
            Log.d(TAG, "saveButton addItem: ${editName.text}, ${editCount.text}")
            viewModel.addItem(editName.text, editCount.text)
            onBackPressed()
        }
    }

    private fun lunchActivityForEdit(){
        viewModel.getItem(itemId)

        saveButton.setOnClickListener {
            viewModel.editItem(editName.text, editCount.text)
            onBackPressed()
        }
    }

    private var mode: String = UNDEFINED_MODE
    private var itemId: Long = UNDEFINED_ID
    private fun parseIntent() {
        if (intent.hasExtra(EXTRA_MODE)) {
            mode = intent.getStringExtra(EXTRA_MODE) ?: UNDEFINED_MODE
            Log.d(TAG, "parseItem: mode: $mode")
            if (mode == ADD_ITEM_MODE) {
                lunchActivityForAdd()
            }
            if (mode == EDIT_ITEM_MODE) {
                itemId = intent.getLongExtra(EXTRA_ITEM_ID, UNDEFINED_ID)
                lunchActivityForEdit()
            }
        }
    }

    private fun registerLiveData() {
        viewModel.itemLiveData.observe(this) {
            editName.setText(it.name)
            editCount.setText(it.count.toString())
        }
    }

    companion object {
        val TAG = "XXXXX"
    }


}