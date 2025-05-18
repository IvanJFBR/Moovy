package com.interview.ivanjfbr.home.ui.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.interview.ivanjfbr.core.ui.getTMDBImageRequest

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movieImageUrl: String?,
    contentDescription: String,
    movieTitle: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .width(120.dp)
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            movieImageUrl?.let {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    painter = rememberAsyncImagePainter(
                        model = getTMDBImageRequest(
                            imageUrl =  it
                        )
                    ),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = contentDescription
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = movieTitle,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}