package com.kaliostech.uffizio.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    
    fun getCurrentDateRange(): Pair<String, String> {
        val dateFormat = SimpleDateFormat(Constants.API_DATE_FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        
        // Start of today (00:00:00)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val fromDate = dateFormat.format(calendar.time)
        
        // Current time
        val toDate = dateFormat.format(Date())
        
        return Pair(fromDate, toDate)
    }
    
    fun formatToDisplayTime(dateString: String): String {
        return try {
            val apiFormat = SimpleDateFormat(Constants.API_DATE_FORMAT, Locale.getDefault())
            val displayFormat = SimpleDateFormat(Constants.DISPLAY_TIME_FORMAT, Locale.getDefault())
            val date = apiFormat.parse(dateString)
            date?.let { displayFormat.format(it) } ?: "N/A"
        } catch (e: Exception) {
            "N/A"
        }
    }
    
    fun parseTimeToSeconds(time: String): Int {
        return try {
            val parts = time.split(":")
            val hours = parts.getOrNull(0)?.toIntOrNull() ?: 0
            val minutes = parts.getOrNull(1)?.toIntOrNull() ?: 0
            val seconds = parts.getOrNull(2)?.toIntOrNull() ?: 0
            hours * 3600 + minutes * 60 + seconds
        } catch (e: Exception) {
            0
        }
    }
    
    fun formatSecondsToHHMM(totalSeconds: Int): String {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        return String.format("%02d:%02d", hours, minutes)
    }
}
