package com.kaliostech.uffizio.domain.model

data class DashboardData(
    val workedTime: String,
    val distance: String,
    val breakTime: String,
    val totalTasks: Int,
    val upcomingTasks: Int,
    val completedTasks: Int,
    val partialTasks: Int,
    val checkInTime: String,
    val checkOutTime: String,
    val totalExpenses: String
)
