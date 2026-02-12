package com.kaliostech.uffizio.dashboard

import android.app.Activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.compose.BackHandler
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaliostech.uffizio.R
import com.kaliostech.uffizio.dashboard.components.DashboardHeader
import com.kaliostech.uffizio.dashboard.components.GoOnDutyButton
import com.kaliostech.uffizio.dashboard.components.TaskSummaryCard
import com.kaliostech.uffizio.dashboard.components.ViewAllTasksButton
import com.kaliostech.uffizio.ui.theme.Geometria
import com.kaliostech.uffizio.ui.theme.GrayBackground
import com.kaliostech.uffizio.ui.theme.InfoBlue
import com.kaliostech.uffizio.ui.theme.PrimaryBlue
import com.kaliostech.uffizio.ui.theme.SuccessGreen
import com.kaliostech.uffizio.ui.theme.UffizioTheme
import com.kaliostech.uffizio.ui.theme.WarningYellow
import com.kaliostech.uffizio.ui.dashboard.DashboardViewModel
import com.kaliostech.uffizio.ui.dashboard.DashboardUiState
import androidx.compose.material3.CircularProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToTaskList: () -> Unit,
    viewModel: DashboardViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = androidx.compose.material3.rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var isRefreshing by remember { mutableStateOf(false) }
    
    // Handle back press to exit app
    BackHandler {
        (context as? Activity)?.finish()
    }

    if (showBottomSheet) {
        com.kaliostech.uffizio.dashboard.components.TodaySummaryBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        )
    }

    Scaffold(
        containerColor = GrayBackground
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
                viewModel.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            when (val state = uiState) {
                is DashboardUiState.Loading -> {
                    // Only show centered spinner if not refreshing (initial load)
                    if (!isRefreshing) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = PrimaryBlue)
                        }
                    }
                    // Update isRefreshing state
                    isRefreshing = true
                }
                is DashboardUiState.Error -> {
                    isRefreshing = false
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Error loading data",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = Geometria
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = state.message,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontFamily = Geometria
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { viewModel.refresh() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryBlue
                            )
                        ) {
                            Text(
                                text = "Retry",
                                fontFamily = Geometria,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                is DashboardUiState.Success -> {
                    isRefreshing = false
                    val data = state.data
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Header with real data
                        DashboardHeader(
                            workedTime = data.workedTime,
                            distance = data.distance,
                            breakTime = data.breakTime,
                            totalTasks = data.totalTasks.toString().padStart(2, '0'),
                            onGoOnDutyClick = { showBottomSheet = true }
                        )
                        
                        // Content below header
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                                .offset(y = (-40).dp)
                        ) {
                            Spacer(modifier = Modifier.height(50.dp))
                            
                            // Task Cards with real data
                            TaskSummaryCard(
                                iconRes = R.drawable.ic_calendar_upcoming,
                                title = "Upcoming",
                                count = data.upcomingTasks.toString().padStart(2, '0'),
                                color = WarningYellow
                            )

                            TaskSummaryCard(
                                iconRes = R.drawable.ic_calendar_completed,
                                title = "Completed",
                                count = data.completedTasks.toString(),
                                color = SuccessGreen
                            )

                            TaskSummaryCard(
                                iconRes = R.drawable.ic_calendar_partial,
                                title = "Partially Completed",
                                count = data.partialTasks.toString(),
                                color = InfoBlue
                            )
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    // View All Tasks Button and FAB inline
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        ViewAllTasksButton(
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp)
                                .padding(bottom = 12.dp),
                            onClick = onNavigateToTaskList
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        FloatingActionButton(
                            onClick = { /* TODO */ },
                            containerColor = PrimaryBlue,
                            contentColor = Color.White,
                            shape = CircleShape,
                            modifier = Modifier.size(60.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add), 
                                contentDescription = "Add Task",
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                    
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    UffizioTheme {
        DashboardScreen(onNavigateToTaskList = {})
    }
}
