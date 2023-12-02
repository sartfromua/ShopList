package com.example.shoplist.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.IllegalArgumentException


class ShopItemFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    private lateinit var inputNameLayout: TextInputLayout
    private lateinit var inputCountLayout: TextInputLayout

    private lateinit var editName: TextInputEditText
    private lateinit var editCount: TextInputEditText

    private lateinit var saveButton: Button

    private lateinit var viewModel: ShopItemViewModel

    private var mode: String = UNDEFINED_MODE
    private var itemID: Long = UNDEFINED_ID

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        setupMode()
        registerLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("XXXXX", "${requireArguments()}")
        with(requireArguments()) {
            if (containsKey(EXTRA_MODE)) {
                mode = getString(EXTRA_MODE) ?: UNDEFINED_MODE
            } else throw IllegalArgumentException("No EXTRA_MODE data!")

            if (mode == EDIT_ITEM_MODE)
                if (containsKey(EXTRA_ITEM_ID)){
                    itemID = getLong(EXTRA_ITEM_ID)
                } else throw IllegalArgumentException("No ItemID data!")
        }
    }


        private fun initViews(view: View) {
        inputNameLayout = view.findViewById(R.id.input_name)
        inputCountLayout = view.findViewById(R.id.input_count)

        editName = view.findViewById(R.id.edit_text_name)
        editName.addTextChangedListener {
            inputNameLayout.error = null
        }
        editCount = view.findViewById(R.id.edit_text_count)
        editCount.addTextChangedListener {
            inputCountLayout.error = null
        }
        saveButton = view.findViewById(R.id.buttonSave)

    }

    private fun setupMode() {
        when (mode) {
            ADD_ITEM_MODE -> setupAdd()
            EDIT_ITEM_MODE -> setupEdit()
            else -> throw IllegalArgumentException("Unknown Mode!")
        }
    }

    private fun setupAdd() {
        saveButton.setOnClickListener {
            viewModel.addItem(editName.text, editCount.text)
        }
    }

    private fun setupEdit() {
        viewModel.getItem(itemID)

        saveButton.setOnClickListener {
            viewModel.editItem(editName.text, editCount.text)
        }
    }

    private fun registerLiveData() {
    viewModel.itemLiveData.observe(viewLifecycleOwner) {
        editName.setText(it.name)
        editCount.setText(it.count.toString())
    }

    viewModel.errorNameLD.observe(viewLifecycleOwner) {
        if (it) inputNameLayout.error = getString(R.string.name_error)
    }

    viewModel.errorCountLD.observe(viewLifecycleOwner) {
        if (it) inputCountLayout.error = getString(R.string.count_error)
    }

    viewModel.finishActivityLD.observe(viewLifecycleOwner) {
        activity?.onBackPressed() // FIXME:
    }
    }

    companion object {
        fun newInstanceFragmentAdd() =  ShopItemFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_MODE, ADD_ITEM_MODE)
            }
        }

        fun newInstanceFragmentEdit(id: Long) =  ShopItemFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_MODE, EDIT_ITEM_MODE)
                putLong(EXTRA_ITEM_ID, id)
            }
        }
    }
}