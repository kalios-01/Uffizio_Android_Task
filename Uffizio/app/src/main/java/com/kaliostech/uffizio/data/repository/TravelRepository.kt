package com.kaliostech.uffizio.data.repository

import com.kaliostech.uffizio.data.model.TravelSummaryResponse
import com.kaliostech.uffizio.util.NetworkResult

interface TravelRepository {
    suspend fun getTravelSummary(
        fromDate: String,
        toDate: String,
        userId: String,
        projectId: String,
        vehicleIds: String
    ): NetworkResult<TravelSummaryResponse>
}
