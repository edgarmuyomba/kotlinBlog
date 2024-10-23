package com.example.kotlinblog.ui.layout.homescreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.kotlinblog.utils.Utilities

@Composable
fun NewsDetailsCard(article: Article, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(article.urlToImage)
                .build(),
            contentDescription = "Image Details Card",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .clip(shape = RoundedCornerShape(10.dp))
                .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Color.Transparent)
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                text = article.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(Modifier.height(10.dp))
            Row {
                Text(
                    text = article.author,
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "•   " + Utilities().getFormalTime(article.publishedAt),
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsDetailsCardPreview() {
    NewsDetailsCard(article = article)
}