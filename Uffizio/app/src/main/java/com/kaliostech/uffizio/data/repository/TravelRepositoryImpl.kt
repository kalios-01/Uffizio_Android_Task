package com.kaliostech.uffizio.data.repository

import com.kaliostech.uffizio.data.model.TravelSummaryResponse
import com.kaliostech.uffizio.data.remote.ApiService
import com.kaliostech.uffizio.util.Constants
import com.kaliostech.uffizio.util.NetworkResult

class TravelRepositoryImpl(
    private val apiService: ApiService
) : TravelRepository {
    
    override suspend fun getTravelSummary(
        fromDate: String,
        toDate: String,
        userId: String,
        projectId: String,
        vehicleIds: String
    ): NetworkResult<TravelSummaryResponse> {
        return try {
            val response = apiService.getTravelSummary(
                action = Constants.ACTION_FILTER,
                method = Constants.METHOD_GET_TRAVEL_SUMMARY,
                fromDate = fromDate,
                toDate = toDate,
                userId = userId,
                userIdAlt = userId,
                vehicleId = vehicleIds,
                projectId = projectId,
                projectIdAlt = projectId,
                screenId = Constants.DEFAULT_SCREEN_ID,
                screenType = Constants.DEFAULT_SCREEN_TYPE,
                entityId = Constants.DEFAULT_ENTITY_ID,
                distanceFilterCondition = Constants.DISTANCE_FILTER_CONDITION
            )
            
            if (response.result == "SUCCESS") {
                NetworkResult.Success(response)
            } else {
                NetworkResult.Error("API returned: ${response.result}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(
                message = e.message ?: "Unknown error occurred",
                exception = e
            )
        }
    }
}
