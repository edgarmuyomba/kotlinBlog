package com.example.kotlinblog.ui.layout.articleDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kotlinblog.models.Article
import com.example.kotlinblog.ui.layout.homescreen.article
import com.example.kotlinblog.utils.Utilities

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsPage(article: Article?, modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    Scaffold { contentPadding ->
        if (article != null) {
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
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                ),
                                startY = 0f,  // Gradient starts from top
                                endY = Float.POSITIVE_INFINITY  // Gradient ends at bottom
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    CustomTopBar(modifier = Modifier.padding(top = 16.dp))
                    Spacer(modifier = Modifier.height(300.dp))
                    Text(
                        text = article.title,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = article.author ?: "Unknown Author",
                            style = TextStyle(
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "  •  " + Utilities().getRelativeTime(article.publishedAt),
                            style = TextStyle(
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .border(
                                width = 0.dp,
                                shape = RoundedCornerShape(25),
                                color = Color.Transparent
                            )
                            .background(color = Color.White, shape = RoundedCornerShape(10))
                            .fillMaxWidth()
                            .padding(24.dp)
                            .defaultMinSize(minHeight = 500.dp)
                    ) {
                        Column(
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = article.source.name ?: "",
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 24.sp,
                                    )
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xff2668d1),
                                            shape = RoundedCornerShape(50)
                                        )
                                        .padding(1.5.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = "Verified",
                                        modifier = Modifier.height(10.dp),
                                        tint = Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = article.content ?: "No content available"
                            )
                        }
                    }
                }
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
                .background(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(50))
                .blur(radius = 1.dp)
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
                    color = Color.Transparent,
                    shape = RoundedCornerShape(50)
                )
                .background(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(50))
                .blur(radius = 1.dp)
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
                    color = Color.Transparent,
                    shape = RoundedCornerShape(50)
                )
                .background(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(50))
                .blur(radius = 1.dp)
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