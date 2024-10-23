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
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinblog.models.Article
import com.example.kotlinblog.state.BlogUiState
import com.example.kotlinblog.state.HomeViewModel
import com.example.kotlinblog.ui.generic.LoadingIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)

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
            HeadlinesScroller(homeViewModel = homeViewModel)
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
fun HeadlinesScroller(homeViewModel: HomeViewModel = viewModel(), modifier: Modifier = Modifier) {

    var articleCount by remember { mutableStateOf(0) }

    val pagerState = rememberPagerState(pageCount = { articleCount })

    val breakingNewsState by homeViewModel.breakingNews.collectAsState()

    when (breakingNewsState) {
        is BlogUiState.Success -> {
            articleCount = (breakingNewsState as BlogUiState.Success).articles.size
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(start = 24.dp, end = 64.dp),
                pageSpacing = 12.dp,
                modifier = modifier.fillMaxWidth(),
                pageSize = PageSize.Fixed(320.dp)
            ) { index ->
                NewsCard(
                    article = (breakingNewsState as BlogUiState.Success).articles[index],
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }
        }

        is BlogUiState.Loading -> {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingIndicator()
                }
            }
        }

        is BlogUiState.Error -> {
            Text(text = "Error")
        }
    }

}

@Composable
fun NewsDetailsCards(homeViewModel: HomeViewModel = viewModel(), modifier: Modifier = Modifier) {

    val recommendedNewsState by homeViewModel.recommendedNews.collectAsState()

    val articles = buildList<Article> { repeat(7) { add(article) } }

    when (recommendedNewsState) {
        is BlogUiState.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items((recommendedNewsState as BlogUiState.Success).articles.size) {
                    NewsDetailsCard(article = (recommendedNewsState as BlogUiState.Success).articles[it])
                }
            }
        }

        is BlogUiState.Loading -> {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingIndicator()
                }
            }
        }

        is BlogUiState.Error -> {
            Text(text = "Error")
        }
    }


}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}