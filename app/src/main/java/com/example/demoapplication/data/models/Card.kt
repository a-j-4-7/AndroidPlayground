package com.example.demoapplication.data.models

data class Card(
    val cardType:String,
    val cardHolderName:String,
    val cardNumber:String,
    val cardExpiryDate:String
){

    companion object{
        fun getListOfCards()= listOf(
            Card("VISA","Sachet Tha Shrestha","4245 **** **** 1158","12/20"),
            Card("Master Card","Merissa Tha Shrestha","2488 **** **** 9541","11/20"),
            Card("SCT","Jake Brown","7415 **** **** 2245","11/20"),
            Card("SCT","Jake Brown","7415 **** **** 2245","11/20"),
            Card("Master Card","Merissa Tha Shrestha","2488 **** **** 9541","11/20"),
            Card("VISA","Sachet Tha Shrestha","4245 **** **** 1158","12/20")
            )
    }
}