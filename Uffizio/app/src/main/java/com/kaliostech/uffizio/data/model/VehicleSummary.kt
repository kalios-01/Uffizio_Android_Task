package com.kaliostech.uffizio.data.model

import com.google.gson.annotations.SerializedName

data class VehicleSummary(
    @SerializedName("VEHICLE_ID")
    val vehicleId: String,
    
    @SerializedName("VEHICLE_NUMBER")
    val vehicleNumber: String,
    
    @SerializedName("VEHICLE_TYPE")
    val vehicleType: String,
    
    @SerializedName("RUNNINGTIME")
    val runningTime: String,
    
    @SerializedName("RUNNINGDISTANCE")
    val runningDistance: Double,
    
    @SerializedName("STOPTIME")
    val stopTime: String,
    
    @SerializedName("IDELTIME")
    val idleTime: String,
    
    @SerializedName("INACTIVETIME")
    val inactiveTime: String,
    
    @SerializedName("AVGSPEED")
    val avgSpeed: String,
    
    @SerializedName("MAXSPEED")
    val maxSpeed: String,
    
    @SerializedName("start_location")
    val startLocation: String,
    
    @SerializedName("end_location")
    val endLocation: String,
    
    @SerializedName("start_odometer")
    val startOdometer: Double,
    
    @SerializedName("end_odometer")
    val endOdometer: Double,
    
    @SerializedName("toll_cost")
    val tollCost: Double,
    
    @SerializedName("work_duration")
    val workDuration: String,
    
    @SerializedName("working_days")
    val workingDays: String,
    
    @SerializedName("driver")
    val driver: String,
    
    @SerializedName("COMPANY")
    val company: String,
    
    @SerializedName("ALERT")
    val alert: String,
    
    @SerializedName("distance_unit")
    val distanceUnit: String,
    
    @SerializedName("SPEEDUNIT")
    val speedUnit: String
)
