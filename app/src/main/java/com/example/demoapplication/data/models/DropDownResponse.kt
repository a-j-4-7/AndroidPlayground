package com.example.demoapplication.data.models


import com.google.gson.annotations.SerializedName

data class DropDownResponse(
    val code: String,
    val `data`: Data,
    val message: String
) {
    data class Data(
        val city: List<City>,
        val country: List<Country>,
        val district: List<District>,
        val state: List<State>
    ) {
        data class City(
            val cityId: Int,
            val cityName: String
        )

        data class Country(
            val countryId: Int,
            val countryName: String
        )

        data class District(
            val districtId: Int,
            val districtName: String
        )

        data class State(
            val stateId: Int,
            val stateName: String
        )
    }
}