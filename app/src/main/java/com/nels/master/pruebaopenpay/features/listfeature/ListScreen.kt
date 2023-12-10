import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.Movie
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.ListMoviesViewModel
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.states.MoviesStates
import com.nels.master.pruebaopenpay.features.locationfeature.viewmodels.MainViewMoldel

/*
@Composable
fun ListScreen(listMoviesViewModel: ListMoviesViewModel) {

    Box(
        modifier = Modifier.background(Color.Green)
    ) {

    }

}*/

@Composable
fun ListScreen(listMoviesViewModel: ListMoviesViewModel) {

    listMoviesViewModel.getAllMovies()
    listMoviesViewModel.getPopularMovies()
    listMoviesViewModel.getTopRatedMovies()

    val allMoviesState = listMoviesViewModel.allMoviesState
    val popularState = listMoviesViewModel.popularMoviesState
    val topRatedState = listMoviesViewModel.topRatedMoviesState

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {

        if (allMoviesState.status == MoviesStates.Status.Success)
            Carrousel(allMoviesState.movies,"Todas las peliculas")
        if (popularState.status == MoviesStates.Status.Success)
            Carrousel(popularState.movies,"Popular")
        if (topRatedState.status == MoviesStates.Status.Success)
            Carrousel(topRatedState.movies,"Top Rated")
    }
    /*
    if (state.status == MoviesStates.Status.Loading) {
        CircularProgressIndicator()
    }

     */
}

@Composable
fun Carrousel(movies:List<Movie>,header:String) {
    Column {
        Text(modifier = Modifier.padding(start = 16.dp),text = header, fontWeight = FontWeight.Bold, fontSize = 20.sp)
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


