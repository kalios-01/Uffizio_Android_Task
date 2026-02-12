package com.kaliostech.uffizio.data.model

import com.google.gson.annotations.SerializedName

data class TravelSummaryResponse(
    @SerializedName("DATA")
    val data: List<VehicleSummary>,
    
    @SerializedName("RESULT")
    val result: String
)
