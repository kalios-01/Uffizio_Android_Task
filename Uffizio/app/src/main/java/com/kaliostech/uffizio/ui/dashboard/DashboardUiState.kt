package com.kaliostech.uffizio.ui.dashboard

import com.kaliostech.uffizio.domain.model.DashboardData

sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val data: DashboardData) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}
