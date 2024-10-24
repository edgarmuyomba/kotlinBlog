package com.example.kotlinblog.ui.layout.articleDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kotlinblog.models.Article
import com.example.kotlinblog.ui.layout.homescreen.article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsPage(article: Article, modifier: Modifier = Modifier) {


    Scaffold { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                model = ImageRequest.Builder(LocalContext.current).data(article.imageUrl)
                    .crossfade(true).build(),
                contentDescription = "image of " + article.title,
                contentScale = ContentScale.Crop
            )
            Column {
                CustomTopBar(modifier = Modifier.padding(16.dp))
            }
        }


    }
}

@Composable
fun CustomTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xffededed),
                    shape = RoundedCornerShape(50)
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
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xffededed),
                    shape = RoundedCornerShape(50)
                )
                .background(color = Color(0xffededed), shape = RoundedCornerShape(50))
        ) {
            IconButton(modifier = Modifier.padding(0.dp), onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.BookmarkBorder,
                    contentDescription = "bookmark"
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xffededed),
                    shape = RoundedCornerShape(50)
                )
                .background(color = Color(0xffededed), shape = RoundedCornerShape(50))
        ) {
            IconButton(modifier = Modifier.padding(0.dp), onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    contentDescription = "more options"
                )
            }
        }
    }
}

@Preview
@Composable
fun ArticleDetailsPagePreview() {
    ArticleDetailsPage(article = article)
}