package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModel
import com.example.shoplist.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddItemActivity : AppCompatActivity() {

//    lateinit var inputNameLayout: TextInputLayout
//    lateinit var inputCountLayout: TextInputLayout

    lateinit var editName: TextInputEditText
    lateinit var editCount: TextInputEditText

    lateinit var saveButton: Button

    lateinit var viewModel: ShopItemViewModel
//    https://github.com/krenevych/Shop/blob/86ff40443886963e097fcb56b7eb256b4c7fc6f9/app/src/main/java/com/example/shop/presentation/ShopItemViewModel.kt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        initViews()
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
            viewModel.addItem(editName.text, editCount.text)
        }
    }

    private fun lunchActivityForEdit(){
//        viewModel.getItem(itemId)

        saveButton.setOnClickListener {
            viewModel.editItem(editName.text, editCount.text)
        }
    }



}