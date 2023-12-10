package com.nels.master.pruebaopenpay.shared.UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.Movie

@Composable
fun Carrousel(movies:List<Movie>, header:String) {
    Column {
        Text(modifier = Modifier.padding(start = 16.dp),text = header, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(9.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            items(movies) {
                CardItem(it)
            }
        }
    }
}