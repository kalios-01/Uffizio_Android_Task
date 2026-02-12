package com.kaliostech.uffizio.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaliostech.uffizio.R
import com.kaliostech.uffizio.dashboard.DashboardScreen
import com.kaliostech.uffizio.ui.theme.Geometria
import com.kaliostech.uffizio.ui.theme.InfoBlue
import com.kaliostech.uffizio.ui.theme.SuccessGreen
import com.kaliostech.uffizio.ui.theme.UffizioTheme
import com.kaliostech.uffizio.ui.theme.WarningYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodaySummaryBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today’s Summary",
                    fontFamily = Geometria,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                IconButton(onClick = onDismissRequest) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close_circle), // Requested Icon
                        contentDescription = "Close",
                        tint = Color.Unspecified // Assume SVG has color or Gray
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFEEEEEE))

            // Date & Duration Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Text(
                        text = "01-06-2023",
                        fontFamily = Geometria,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Thursday",
                        fontFamily = Geometria,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "8h 43m",
                        fontFamily = Geometria,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_timer_fill), // Requested Icon
                        contentDescription = null,
                        tint = SuccessGreen,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Timeline Section
            Column(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                // On Duty Item
                TimelineItem(
                    time = "07:34 AM",
                    label = "On Duty",
                    address = "35/616, Alam Ganj, Lohamandi, Agra, Uttar Pradesh (SE)",
                    iconRes = R.drawable.ic_check_in, // Requested Icon
                    isStart = true
                )
                
                // Off Duty Item
                TimelineItem(
                    time = "05:50 PM",
                    label = "Off Duty",
                    address = "Ragendra Swarup Public School, Sector 10, Agra, Uttar Pradesh (SE)",
                    iconRes = R.drawable.ic_check_out, // Requested Icon
                    isStart = false
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE))
            Spacer(modifier = Modifier.height(16.dp))

            // Tasks Section
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontFamily = Geometria, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)) {
                            append("18 ")
                        }
                        withStyle(style = SpanStyle(fontFamily = Geometria, fontSize = 16.sp, color = Color.Gray)) {
                            append("Tasks")
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                // Stats Grid
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Left Column
                    Column(modifier = Modifier.weight(1f)) {
                        StatItem(
                            iconRes = R.drawable.ic_calendar_upcoming,
                            label = "Upcoming",
                            value = "11 Tasks",
                            valueColor = WarningYellow
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        StatItem(
                            iconRes = R.drawable.ic_break, // Using Header break icon or requested ic_coffee?
                            label = "Break",
                            value = "1h 30m",
                            valueColor = Color.Black,
                            iconTint = Color.Gray
                        )
                    }
                    
                    // Right Column
                    Column(modifier = Modifier.weight(1f)) {
                        StatItem(
                            iconRes = R.drawable.ic_calendar_completed,
                            label = "Completed",
                            value = "06 Tasks",
                            valueColor = SuccessGreen
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        StatItem(
                            iconRes = R.drawable.ic_expense, // Requested Icon
                            label = "Expense",
                            value = "$550.00",
                            valueColor = Color.Black,
                            iconTint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TimelineItem(
    time: String,
    label: String,
    address: String,
    iconRes: Int,
    isStart: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth().height(80.dp)) {
        // Time Column
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.width(70.dp)
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray,
                fontFamily = Geometria
            )
            Text(
                text = time,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = Geometria
            )
        }
        
        // Icon and Line Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            if (isStart) {
                // Dotted Line
                Canvas(modifier = Modifier.width(1.dp).weight(1f)) {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                }
            }
        }
        
        // Address Column
        Text(
            text = address,
            fontSize = 13.sp,
            color = Color.Black,
            fontFamily = Geometria,
            lineHeight = 18.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun StatItem(
    iconRes: Int,
    label: String,
    value: String,
    valueColor: Color,
    iconTint: Color = Color.Unspecified
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(16.dp) // Reduced from 20.dp
        )
        Spacer(modifier = Modifier.width(6.dp)) // Reduced from 8.dp
        Text(
            text = label,
            fontSize = 12.sp, // Reduced from 14.sp
            color = Color.Gray,
            fontFamily = Geometria
        )
        Spacer(modifier = Modifier.width(6.dp)) // Reduced from 8.dp
        Text(
            text = value,
            fontSize = 13.sp, // Reduced from 14.sp
            fontWeight = FontWeight.Bold,
            color = valueColor,
            fontFamily = Geometria
        )
    }
}
