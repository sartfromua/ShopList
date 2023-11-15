package com.example.shoplist.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
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

        class ShopItemViewHolder(view: View):
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

    var clickListener: ((view: View, item: ShopItem)->Unit  )? = null
    var longClickListener: ((view: View, item: ShopItem)->Unit  )? = null

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item: ShopItem = shopItems[position]
        with(holder) {
            name.text = "${item.name} (activity: ${item.isActive})"
            count.text = item.count.toString()
            if (item.isActive) {
                cardView.setBackgroundColor(Color.MAGENTA)
            } else cardView.setBackgroundColor(Color.DKGRAY)

            cardView.setOnClickListener {
                clickListener?.invoke(cardView, item)
            }
            cardView.setOnLongClickListener {
                longClickListener?.invoke(cardView, item)
                true
            }
        }


    }

}