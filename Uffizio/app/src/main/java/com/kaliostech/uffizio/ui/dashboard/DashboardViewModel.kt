package com.kaliostech.uffizio.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaliostech.uffizio.data.remote.RetrofitClient
import com.kaliostech.uffizio.data.repository.TravelRepositoryImpl
import com.kaliostech.uffizio.domain.mapper.TravelSummaryMapper
import com.kaliostech.uffizio.util.Constants
import com.kaliostech.uffizio.util.DateUtils
import com.kaliostech.uffizio.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    
    private val repository = TravelRepositoryImpl(RetrofitClient.apiService)
    
    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
    
    init {
        loadDashboardData()
    }
    
    fun loadDashboardData(
        userId: String = Constants.DEFAULT_USER_ID,
        projectId: String = Constants.DEFAULT_PROJECT_ID,
        vehicleIds: String = "484731,610258,610249,610266,610244" // From your API example
    ) {
        viewModelScope.launch {
            _uiState.value = DashboardUiState.Loading
            
            val (fromDate, toDate) = DateUtils.getCurrentDateRange()
            
            when (val result = repository.getTravelSummary(
                fromDate = fromDate,
                toDate = toDate,
                userId = userId,
                projectId = projectId,
                vehicleIds = vehicleIds
            )) {
                is NetworkResult.Success -> {
                    val dashboardData = TravelSummaryMapper.mapToDashboardData(
                        response = result.data,
                        fromDate = fromDate,
                        toDate = toDate
                    )
                    _uiState.value = DashboardUiState.Success(dashboardData)
                }
                is NetworkResult.Error -> {
                    _uiState.value = DashboardUiState.Error(result.message)
                }
                is NetworkResult.Loading -> {
                    _uiState.value = DashboardUiState.Loading
                }
            }
        }
    }
    
    fun refresh() {
        loadDashboardData()
    }
}
