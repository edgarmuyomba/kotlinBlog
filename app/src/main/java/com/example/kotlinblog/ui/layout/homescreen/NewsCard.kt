package com.example.kotlinblog.ui.layout.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kotlinblog.models.Article
import com.example.kotlinblog.models.Source
import com.example.kotlinblog.utils.Utilities

@Composable
fun NewsCard(
    article: Article,
    onCardClicked: (article: Article) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(shape = RoundedCornerShape(10.dp), width = 1.dp, color = Color.White)
            .clickable { onCardClicked(article) }
    ) {
        article.imageUrl?.let {
            AsyncImage(
                modifier = Modifier
                    .border(
                        shape = RoundedCornerShape(10.dp),
                        width = 1.dp,
                        color = Color.White
                    )
                    .fillMaxSize(),
                model = ImageRequest.Builder(context = LocalContext.current).data(article.imageUrl)
                    .crossfade(true).build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Article Image",
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
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
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = TextStyle(
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(5.dp))
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
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "• ${Utilities().getRelativeTime(article.publishedAt)}",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Light
                    )
                )
            }
            Text(
                text = article.title,
                style = TextStyle(
                    color = Color.White,
                )
            )
        }

    }
}

val article = Article(
    source = Source(id = "cnn", name = "CNN"),
    author = "Devan Cole",
    title = "Georgia Supreme Court maintains block on controversial election rules from Trump allies - CNN",
    description = "The Georgia Supreme Court won’t let the state election board enforce a slate of controversial new election rules that were passed by allies of Donald Trump, ruling Tuesday against Republicans who asked for",
    url = "https://www.cnn.com/2024/10/22/politics/georgia-supreme-court-maintains-block-on-controversial-new-election-rules/index.html",
    imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/c-ap24121610846153.jpg?c=16x9&q=w_800,c_fill",
    publishedAt = "2024-10-23T02:30:00Z",
    content = "The Georgia Supreme Court wont let the state election board enforce a slate of controversial new election rules that were passed by allies of Donald Trump, ruling Tuesday against Republicans who asked..."
)