package com.kaliostech.uffizio.dashboard.components

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaliostech.uffizio.R
import com.kaliostech.uffizio.ui.theme.Geometria
import com.kaliostech.uffizio.ui.theme.UffizioTheme

@Composable
fun DashboardHeader(
    modifier: Modifier = Modifier,
    workedTime: String = "00:00",
    distance: String = "0.0",
    breakTime: String = "00:00",
    totalTasks: String = "00",
    onGoOnDutyClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.headerbg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Menu",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Hello, Laurence",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = Geometria,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Last Checked-in 08:00 AM",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 12.sp,
                        fontFamily = Geometria
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { }) {
                         Icon(
                            painter = painterResource(id = R.drawable.ic_announcement),
                            contentDescription = "Announcements",
                            tint = Color.White,
                             modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sos),
                            contentDescription = "SOS",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Worked",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = Geometria
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = workedTime,
                            color = Color.White,
                            fontSize = 48.sp,
                            fontFamily = Geometria,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 48.sp
                        )
                        Text(
                            text = " hrs",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = Geometria,
                            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.Start) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, 
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                         Icon(
                            painter = painterResource(id = R.drawable.ic_distance),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Distance", 
                            color = Color.White, 
                            fontSize = 14.sp, 
                            fontFamily = Geometria
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = distance, 
                            color = Color.White, 
                            fontWeight = FontWeight.Bold, 
                            fontSize = 14.sp, 
                            fontFamily = Geometria
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "km", 
                            color = Color.White, 
                            fontSize = 12.sp, 
                            fontFamily = Geometria
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                         Icon(
                            painter = painterResource(id = R.drawable.ic_break),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Break", 
                            color = Color.White, 
                            fontSize = 14.sp, 
                            fontFamily = Geometria
                        )
                         Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = breakTime, 
                            color = Color.White, 
                            fontWeight = FontWeight.Bold, 
                            fontSize = 14.sp, 
                            fontFamily = Geometria
                        )
                         Spacer(modifier = Modifier.width(2.dp))
                         Text(
                             text = "hrs", 
                             color = Color.White, 
                             fontSize = 12.sp, 
                             fontFamily = Geometria
                         )
                    }
                }
            }


            Spacer(modifier = Modifier.weight(1f))

            // "05 Tasks" and "Go On Duty" Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // "05 Tasks" on the left
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = totalTasks,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Geometria,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Tasks",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontFamily = Geometria,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                // "Go On Duty" button on the right
                com.kaliostech.uffizio.dashboard.components.GoOnDutyButton(
                    onClick = onGoOnDutyClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardHeaderPreview() {
    UffizioTheme {
        DashboardHeader(
            workedTime = "02:36",
            distance = "12.3",
            breakTime = "01:30",
            totalTasks = "05",
            onGoOnDutyClick = {}
        )
    }
}
