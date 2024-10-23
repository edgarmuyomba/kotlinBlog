package com.example.kotlinblog.ui.layout.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinblog.models.Article

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
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Breaking News",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
                TextButton(
                    onClick = {}
                ) {
                    Text(
                        text = "View all",
                        style = TextStyle(
                            color = Color(0xff2668d1)
                        )
                    )
                }
            }
            HeadlinesScroller()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = "Recommendation",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
                TextButton(
                    onClick = {}
                ) {
                    Text(
                        text = "View all",
                        style = TextStyle(
                            color = Color(0xff2668d1)
                        )
                    )
                }
            }
            NewsDetailsCards()
        }
    }
}

@Composable
fun HeadlinesScroller(modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState(pageCount = { 7 })

    val articles = buildList<Article> { repeat(7) { add(article) } }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(start = 24.dp, end = 64.dp),
        pageSpacing = 12.dp,
        modifier = Modifier.fillMaxWidth(),
        pageSize = PageSize.Fixed(320.dp)
    ) { index ->
        NewsCard(
            article = articles[index], modifier = Modifier
                .fillMaxWidth()
        )

    }
}

@Composable
fun NewsDetailsCards(modifier: Modifier = Modifier) {
    val articles = buildList<Article> { repeat(7) { add(article) } }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(articles.size) {
            NewsDetailsCard(article = articles[it])
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}