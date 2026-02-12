package com.kaliostech.uffizio.data.remote

import com.kaliostech.uffizio.data.model.TravelSummaryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    
    @GET("mobileservice")
    suspend fun getTravelSummary(
        @Query("Action") action: String,
        @Query("method") method: String,
        @Query("fromDate") fromDate: String,
        @Query("toDate") toDate: String,
        @Query("userId") userId: String,
        @Query("user_id") userIdAlt: String,
        @Query("vehicleId") vehicleId: String,
        @Query("ProjectId") projectId: String,
        @Query("project_id") projectIdAlt: String,
        @Query("ScreenId") screenId: String,
        @Query("ScreenType") screenType: String,
        @Query("EntityId") entityId: String,
        @Query("distance_filter_condition") distanceFilterCondition: String,
        @Query("distance_filter_value") distanceFilterValue: String = "",
        @Query("SubAction") subAction: String = ""
    ): TravelSummaryResponse
}
