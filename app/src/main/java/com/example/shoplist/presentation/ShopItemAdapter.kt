package com.example.shoplist.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem

class ShopItemAdapter:
    RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder>() {

    var shopItems = listOf<ShopItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameTextView)
        val count: TextView = view.findViewById(R.id.countTextView)
        val cardView: CardView = view.findViewById(R.id.cardView_shopItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_shop_item, parent, false)

        return ShopItemViewHolder(view)
    }

    override fun getItemCount() = shopItems.size

    var clickListener: ((view: View, item: ShopItem) -> Unit)? = null
    var longClickListener: ((view: View, item: ShopItem) -> Unit)? = null

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item: ShopItem = shopItems[position]
        with(holder) {
            name.text = "${item.name} (activity: ${item.isActive})"
            count.text = item.count.toString()
            cardView.findViewById<ImageView>(R.id.crossedImageView).visibility =
                if (item.isActive) View.INVISIBLE
                else View.VISIBLE

            cardView.setOnClickListener {
                clickListener?.invoke(cardView, item)
            }
            cardView.setOnLongClickListener {
                longClickListener?.invoke(cardView, item)
                true
            }
        }
    }


    var swipeListener: ((item: ShopItem) -> Unit)? = null
    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//                    or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            val shopItem = shopItems[position]
            swipeListener?.invoke(shopItem)
        }

    }
}