import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tp1.R
import com.example.tp1.ui.theme.MainViewModel

@Composable
fun serie(viewModel: MainViewModel, navController: NavController) {
    val id = navController.currentBackStackEntry?.arguments?.getString("id")
    val serie by viewModel.serieDetails.collectAsState()
    val cast by viewModel.serieCast.collectAsState()

    if (serie.isEmpty() && cast.isEmpty()) {
        viewModel.getSerieById(id.toString())
        viewModel.serieCast(id.toString())
    }

    Column(modifier = Modifier.fillMaxSize()) {

        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier.padding(top = 16.dp)

        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_left_24),
                contentDescription = "Back",
                Modifier.size(80.dp),
                tint = Color(0xFFA64D79)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()

        ) {
            items(serie) { s ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = s.name,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA64D79),
                        fontSize = 40.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${s.backdrop_path}",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${s.poster_path}",
                                contentDescription = null,
                                modifier = Modifier.size(220.dp)
                            )
                            Spacer(modifier = Modifier.height(130.dp))
                            Column {
                                Text(
                                    text = "Sortie le: " + s.first_air_date,
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                                Text(
                                    text = s.genres.joinToString(", ") { it.name },
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Synopsis",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFA64D79),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = s.overview,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Têtes d'affiche",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA64D79),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    cast.chunked(2).forEach { rowCast ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            rowCast.forEach { c ->
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(1f)
                                ) {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500${c.profile_path}",
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(300.dp)
                                            .fillMaxWidth()
                                    )
                                    Text(
                                        text = c.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 23.sp,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
