package com.kaliostech.uffizio.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaliostech.uffizio.R
import com.kaliostech.uffizio.ui.theme.Geometria
import com.kaliostech.uffizio.ui.theme.InfoBlue
import com.kaliostech.uffizio.ui.theme.SuccessGreen

@Composable
fun GoOnDutyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
     Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(25.dp), // Pill shape
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = null,
                tint = SuccessGreen
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Go On Duty", 
                color = SuccessGreen,
                fontFamily = Geometria,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ViewAllTasksButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(25.dp), // Rounded corners
        border = BorderStroke(1.dp, InfoBlue),
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
         Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_view_list),
                contentDescription = null,
                tint = InfoBlue
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "View all tasks", 
                color = InfoBlue,
                fontFamily = Geometria,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
