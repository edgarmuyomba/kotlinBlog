package com.example.kotlinblog.ui.layout.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp, color = Color(0xffdadada), shape = RoundedCornerShape(50)
                    )
                    .background(color = Color(0xffdadada), shape = RoundedCornerShape(50))
            ) {
                IconButton(
                    modifier = Modifier.padding(0.dp),
                    onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = "Notifications"
                    )
                }
            }
        }, actions = {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp, color = Color(0xffdadada), shape = RoundedCornerShape(50)
                    )
                    .background(color = Color(0xffdadada), shape = RoundedCornerShape(50))
            ) {
                IconButton(
                    modifier = Modifier.padding(0.dp),
                    onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Search, contentDescription = "Search"
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp, color = Color(0xffdadada), shape = RoundedCornerShape(50)
                    )
                    .background(color = Color(0xffdadada), shape = RoundedCornerShape(50))
            ) {
                IconButton(
                    modifier = Modifier.padding(0.dp),
                    onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications"
                    )
                }
            }
        })
    }) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            Text("Home Screen")

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}