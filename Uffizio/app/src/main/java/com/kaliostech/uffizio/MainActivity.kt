package com.kaliostech.uffizio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kaliostech.uffizio.dashboard.DashboardScreen
import com.kaliostech.uffizio.task_list.TaskListScreen
import com.kaliostech.uffizio.ui.theme.UffizioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UffizioTheme {
                 var currentScreen by remember { mutableStateOf("dashboard") }

                 if (currentScreen == "dashboard") {
                     DashboardScreen(
                         onNavigateToTaskList = { currentScreen = "taskList" }
                     )
                 } else {
                     TaskListScreen(
                         onBack = { currentScreen = "dashboard" }
                     )
                 }
            }
        }
    }
}
