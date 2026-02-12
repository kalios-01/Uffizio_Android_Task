package com.kaliostech.uffizio.task_list

import android.app.Activity

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.activity.compose.BackHandler
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaliostech.uffizio.R
import com.kaliostech.uffizio.ui.theme.Geometria
import com.kaliostech.uffizio.ui.theme.PrimaryBlue
import com.kaliostech.uffizio.ui.theme.SuccessGreen
import com.kaliostech.uffizio.ui.theme.WarningYellow

@Composable
fun TaskListScreen(onBack: () -> Unit = {}) {
    val context = LocalContext.current
    var selectedFilter by remember { mutableStateOf("All") }
    
    // Handle back press to navigate back to Dashboard
    BackHandler {
        onBack()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = PrimaryBlue,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_fab),
                    contentDescription = "Add Task"
                )
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar_date),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "16 May, Tue",
                        fontFamily = Geometria,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
                Text(
                    text = "Map view",
                    fontFamily = Geometria,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = PrimaryBlue,
                    modifier = Modifier.clickable { /* TODO */ }
                )
            }

            // Filters
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FilterButton("All", R.drawable.ic_filter_all, selectedFilter == "All") { selectedFilter = "All" }
                FilterButton("High", R.drawable.ic_filter_high, selectedFilter == "High") { selectedFilter = "High" }
                FilterButton("Medium", R.drawable.ic_filter_medium, selectedFilter == "Medium") { selectedFilter = "Medium" }
                FilterButton("Low", R.drawable.ic_filter_low, selectedFilter == "Low") { selectedFilter = "Low" }
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            // Task List
            LazyColumn {
                item {
                    SectionHeader("Upcoming", "02 tasks", WarningYellow)
                    Spacer(modifier = Modifier.height(12.dp))
                }
                
                item {
                     TaskCard(
                        title = "Machine repair",
                        time = "EST 10:40 AM",
                        priority = "Medium",
                        type = "Installation",
                        status = "Upcoming",
                        progress = 0.75f,
                        progressText = "3/4 tasks",
                        iconRes = R.drawable.ic_task_upcoming
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    SectionHeader("Partially Completed", "02 tasks", Color(0xFF4053FF))
                    Spacer(modifier = Modifier.height(12.dp))
                }
                
                item {
                     TaskCard(
                        title = "Water bottle delivery",
                        time = "EST 10:40 AM | ACT 10:45 AM",
                        priority = "Medium",
                        type = "Installation",
                        status = "Partial",
                        iconRes = R.drawable.ic_task_partial
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                 item {
                    SectionHeader("Missed", "01 task", Color.Red)
                    Spacer(modifier = Modifier.height(12.dp))
                }
                
                item {
                     TaskCard(
                        title = "AC Installation",
                        time = "EST 10:40 AM",
                        priority = "Medium",
                        type = "Installation",
                        status = "Missed",
                        iconRes = R.drawable.ic_task_missed
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                
                item {
                    SectionHeader("Completed", "02 tasks", SuccessGreen)
                    Spacer(modifier = Modifier.height(12.dp))
                }
                
                item {
                     TaskCard(
                        title = "Washing machine motor repair",
                        time = "EST 10:40 AM | ACT 10:45 AM",
                        priority = "Medium",
                        type = "Installation",
                        status = "Completed",
                        iconRes = R.drawable.ic_task_completed
                    )
                    Spacer(modifier = Modifier.height(80.dp)) // Bottom padding for FAB
                }
            }
        }
    }
}

@Composable
fun FilterButton(text: String, iconRes: Int, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) PrimaryBlue else Color(0xFFF5F5F5)
    val iconColor = if (isSelected) Color.White else PrimaryBlue
    val textColor = if (isSelected) Color.White else Color.Gray

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontFamily = Geometria,
            fontSize = 12.sp,
            color = textColor
        )
    }
}

@Composable
fun SectionHeader(title: String, count: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = Geometria,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = color
        )
        Text(
            text = count,
            fontFamily = Geometria,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = color
        )
    }
}

@Composable
fun TaskCard(
    title: String,
    time: String,
    priority: String,
    type: String,
    status: String,
    progress: Float? = null,
    progressText: String? = null,
    iconRes: Int
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon and Tags
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    // Priority Tag
                    Box(
                        modifier = Modifier
                            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = priority,
                            fontFamily = Geometria,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = type,
                        fontFamily = Geometria,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                
                // Progress (Optional) or Chevron
                if (progress != null && progressText != null) {
                   //
                } else {
                     Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            // Progress Bar row if applicable (Custom layout need to match design properly)
             if (progress != null && progressText != null) {
                  Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.End,
                      verticalAlignment = Alignment.CenterVertically
                  ) {
                       Column(horizontalAlignment = Alignment.End) {
                           Row(verticalAlignment = Alignment.CenterVertically) {
                               Text(
                                   text = progressText,
                                   fontFamily = Geometria,
                                   fontWeight = FontWeight.Bold,
                                   fontSize = 10.sp,
                                   color = Color.Gray
                               )
                               Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_chevron_right),
                                    contentDescription = null,
                                    tint = PrimaryBlue,
                                    modifier = Modifier.size(16.dp)
                                )
                           }
                           
                           LinearProgressIndicator(
                               progress = { progress },
                               modifier = Modifier.width(60.dp).height(4.dp).clip(RoundedCornerShape(2.dp)),
                               color = SuccessGreen,
                               trackColor = Color(0xFFEEEEEE),
                           )
                       }
                  }
             }

            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = title,
                fontFamily = Geometria,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = time,
                fontFamily = Geometria,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen()
}
