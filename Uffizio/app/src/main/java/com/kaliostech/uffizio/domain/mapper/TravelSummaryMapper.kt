package com.kaliostech.uffizio.domain.mapper

import com.kaliostech.uffizio.data.model.TravelSummaryResponse
import com.kaliostech.uffizio.data.model.VehicleSummary
import com.kaliostech.uffizio.domain.model.DashboardData
import com.kaliostech.uffizio.util.DateUtils

object TravelSummaryMapper {
    
    fun mapToDashboardData(
        response: TravelSummaryResponse,
        fromDate: String,
        toDate: String
    ): DashboardData {
        val vehicles = response.data
        
        return DashboardData(
            workedTime = calculateTotalWorkedTime(vehicles),
            distance = calculateTotalDistance(vehicles),
            breakTime = calculateTotalBreakTime(vehicles),
            totalTasks = vehicles.size,
            upcomingTasks = countUpcomingTasks(vehicles),
            completedTasks = countCompletedTasks(vehicles),
            partialTasks = countPartialTasks(vehicles),
            checkInTime = DateUtils.formatToDisplayTime(fromDate),
            checkOutTime = DateUtils.formatToDisplayTime(toDate),
            totalExpenses = calculateTotalExpenses(vehicles)
        )
    }
    
    private fun calculateTotalWorkedTime(vehicles: List<VehicleSummary>): String {
        val totalSeconds = vehicles.sumOf { DateUtils.parseTimeToSeconds(it.runningTime) }
        return DateUtils.formatSecondsToHHMM(totalSeconds)
    }
    
    private fun calculateTotalDistance(vehicles: List<VehicleSummary>): String {
        val total = vehicles.sumOf { it.runningDistance }
        return String.format("%.1f", total)
    }
    
    private fun calculateTotalBreakTime(vehicles: List<VehicleSummary>): String {
        val totalSeconds = vehicles.sumOf { 
            DateUtils.parseTimeToSeconds(it.stopTime) + 
            DateUtils.parseTimeToSeconds(it.idleTime)
        }
        return DateUtils.formatSecondsToHHMM(totalSeconds)
    }
    
    private fun countUpcomingTasks(vehicles: List<VehicleSummary>): Int {
        return vehicles.count { it.runningTime == "00:00:00" }
    }
    
    private fun countCompletedTasks(vehicles: List<VehicleSummary>): Int {
        return vehicles.count { vehicle ->
            vehicle.runningTime != "00:00:00" && 
            DateUtils.parseTimeToSeconds(vehicle.stopTime) > 
            DateUtils.parseTimeToSeconds(vehicle.runningTime)
        }
    }
    
    private fun countPartialTasks(vehicles: List<VehicleSummary>): Int {
        return vehicles.count { vehicle ->
            vehicle.runningTime != "00:00:00" && 
            vehicle.inactiveTime == "00:00:00"
        }
    }
    
    private fun calculateTotalExpenses(vehicles: List<VehicleSummary>): String {
        val total = vehicles.sumOf { it.tollCost }
        return "₹${total.toInt()}"
    }
}
