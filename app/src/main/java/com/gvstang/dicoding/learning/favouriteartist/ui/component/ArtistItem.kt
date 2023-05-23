package com.gvstang.dicoding.learning.favouriteartist.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gvstang.dicoding.learning.favouriteartist.R
import com.gvstang.dicoding.learning.favouriteartist.ui.theme.FavouriteArtistTheme

@Composable
fun ArtistItem(
    image: Int,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            modifier = Modifier
                .width(170.dp)
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

@Preview
@Composable
fun ArtistItemPreview() {
    FavouriteArtistTheme {
        ArtistItem(
            image = R.drawable.black_sabbath,
            name = "Black Sabbath jajajajajaja"
        )
    }
}