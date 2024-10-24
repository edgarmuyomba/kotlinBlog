package com.example.kotlinblog.ui.layout.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinblog.state.BlogUiState
import com.example.kotlinblog.state.SearchViewModel
import com.example.kotlinblog.state.Tag
import com.example.kotlinblog.ui.generic.LoadingIndicator
import com.example.kotlinblog.ui.layout.homescreen.NewsDetailsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(modifier: Modifier = Modifier) {

    val searchViewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)

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
            SearchField(searchViewModel = searchViewModel)
            Tags(searchViewModel = searchViewModel)
            Articles(searchViewModel = searchViewModel)
        }

    }
}

@Composable
fun SearchField(searchViewModel: SearchViewModel = viewModel(), modifier: Modifier = Modifier) {

    val searchQuery by searchViewModel.searchQuery.collectAsState()

    Row {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchViewModel.updateSearchQuery(it)
            },
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchViewModel.clearSearchQuery() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Clear Search"
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xffededed),
                focusedContainerColor = Color(0xffededed),
            ),
            modifier = modifier
                .weight(1f)
                .clip(RoundedCornerShape(50.dp))
                .border(width = 0.dp, shape = RoundedCornerShape(50.dp), color = Color.Transparent)
        )
        Spacer(modifier = Modifier.width(10.dp))
        AnimatedVisibility(
            visible = searchQuery.isNotEmpty()
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        color = Color(0xff1976d2)
                    )
                    .border(
                        width = 0.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(50)
                    )
            ) {
                IconButton(
                    onClick = {
                        searchViewModel.search()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun Tags(searchViewModel: SearchViewModel = viewModel(), modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    val selectedTag by searchViewModel.selectedTag.collectAsState()

    val showSearchResults by searchViewModel.showSearchResults.collectAsState()
    val searchQuery by searchViewModel.searchQuery.collectAsState()

    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (showSearchResults) {
            Text(
                "Search Results: $searchQuery",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold
                )
            )
        } else {
            TagCard(
                tag = Tag.General,
                onClick = { searchViewModel.setSelectedTag(Tag.General) },
                selected = Tag.General == selectedTag
            )
            TagCard(
                tag = Tag.Business,
                onClick = { searchViewModel.setSelectedTag(Tag.Business) },
                selected = Tag.Business == selectedTag
            )
            TagCard(
                tag = Tag.Entertainment,
                onClick = { searchViewModel.setSelectedTag(Tag.Entertainment) },
                selected = Tag.Entertainment == selectedTag
            )
            TagCard(
                tag = Tag.Health,
                onClick = { searchViewModel.setSelectedTag(Tag.Health) },
                selected = Tag.Health == selectedTag
            )
            TagCard(
                tag = Tag.Science,
                onClick = { searchViewModel.setSelectedTag(Tag.Science) },
                selected = Tag.Science == selectedTag
            )
            TagCard(
                tag = Tag.Technology,
                onClick = { searchViewModel.setSelectedTag(Tag.Technology) },
                selected = Tag.Technology == selectedTag
            )
            TagCard(
                tag = Tag.Sports,
                onClick = { searchViewModel.setSelectedTag(Tag.Sports) },
                selected = Tag.Sports == selectedTag
            )
        }

    }
}

@Composable
fun Articles(searchViewModel: SearchViewModel = viewModel(), modifier: Modifier = Modifier) {

    val showSearchResults by searchViewModel.showSearchResults.collectAsState()

    val articlesState by remember(showSearchResults) {
        if (showSearchResults) {
            searchViewModel.searchResults
        } else {
            searchViewModel.tagResults
        }
    }.collectAsState()

    when (articlesState) {
        is BlogUiState.Success -> {
            val successState = articlesState as BlogUiState.Success
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(successState.articles.size) {
                    NewsDetailsCard(article = successState.articles[it])
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
fun SearchPagePreview() {
    SearchPage()
}