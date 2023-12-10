package com.nels.master.pruebaopenpay.shared.UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.Movie

@Composable
fun CardItem(movie:Movie) {
    Card(
        modifier = Modifier
            .size(width = 140.dp, height = 210.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.
                Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original/".plus( movie.poster_path))
                    .crossfade(true)
                    .scale(Scale.FIT).build()
                ,
                contentDescription = null
            )
        }
    }
}