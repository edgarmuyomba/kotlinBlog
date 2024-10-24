package com.example.kotlinblog.ui.layout.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinblog.state.Tag

@Composable
fun TagCard(tag: Tag, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(
                0.dp, shape = RoundedCornerShape(50.dp), color = Color.Transparent
            )
            .clip(RoundedCornerShape(50.dp))
            .background(
                color = if (selected) Color(0xff2668d1) else Color(0xffededed)
            )
            .clickable { onClick() }
    ) {
        Text(
            text = tag.value,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = if (selected) Color.White else Color.Gray
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)

        )
    }
}