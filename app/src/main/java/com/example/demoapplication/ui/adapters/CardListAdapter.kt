package com.example.demoapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.R
import com.example.demoapplication.data.models.Card

class CardListAdapter(private val listOfCards:List<Card>): RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        return CardListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_list_item_layout,parent,false))
    }

    override fun getItemCount(): Int = listOfCards.size

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        holder.bindView(listOfCards[position])
    }


    inner class CardListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val tvCardType = itemView.findViewById<TextView>(R.id.tvCardType)
        val tvCardHolderName = itemView.findViewById<TextView>(R.id.tvCardHolderName)
        val tvCardNumber = itemView.findViewById<TextView>(R.id.tvCardNumber)
        val tvCardExpiryDate = itemView.findViewById<TextView>(R.id.tvCardExpiryDate)

        fun bindView(card: Card) {
            with(card){
                tvCardType.text = cardType
                tvCardHolderName.text = cardHolderName
                tvCardNumber.text = cardNumber
                tvCardExpiryDate.text = cardExpiryDate
            }
        }

    }

}