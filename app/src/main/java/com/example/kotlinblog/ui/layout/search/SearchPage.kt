package com.example.kotlinblog.ui.layout.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp, color = Color(0xffededed), shape = RoundedCornerShape(50)
                    )
                    .background(color = Color(0xffededed), shape = RoundedCornerShape(50))
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back Button"
                    )
                }
            }
        })

    }) { contentPadding ->
        Column(
            modifier = modifier
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Discover", style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            )
            Text(
                text = "News from around the world", style = TextStyle(
                    fontWeight = FontWeight.Light,
                    color = Color.Gray,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchField()
            Tags()
        }

    }
}

@Composable
fun SearchField(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "Search"
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xffededed),
            focusedContainerColor = Color(0xffededed),
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
            .border(width = 0.dp, shape = RoundedCornerShape(50.dp), color = Color.Transparent)
    )
}

@Composable
fun Tags(modifier: Modifier = Modifier) {

    val _tags = listOf("All", "Politics", "Sports", "Education", "Games")

    LazyRow(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(_tags.size) { index ->
            TagCard(text = _tags[index], selected = false)
        }
    }
}

@Preview
@Composable
fun SearchPagePreview() {
    SearchPage()
}