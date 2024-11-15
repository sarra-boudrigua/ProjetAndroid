package com.example.tp1.SeriesFile

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
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import com.example.tp1.R
import com.example.tp1.MainViewModel

@Composable
fun serie(windowSizeClass: WindowSizeClass, viewModel: MainViewModel, navController: NavController) {
    val id = navController.currentBackStackEntry?.arguments?.getString("id")
    val serie by viewModel.serieDetails.collectAsState()
    val cast by viewModel.serieCast.collectAsState()

    if (serie.isEmpty() && cast.isEmpty()) {
        viewModel.getSerieById(id.toString())
        viewModel.serieCast(id.toString())
    }
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(serie) { s ->
                Box {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${s.backdrop_path}",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .height(300.dp)
                    )

                    IconButton(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier
                            .padding(top = 29.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_keyboard_arrow_left_24),
                            contentDescription = "Back",
                            modifier = Modifier.size(35.dp),
                            tint = Color(0xFFD4BEE4)
                        )
                    }

                    Column(modifier = Modifier.padding(start = 8.dp, top = 220.dp)) {
                        Text(
                            text = s.name,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 25.sp,

                            )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${s.poster_path}",
                            contentDescription = null,
                            modifier = Modifier
                                .width(150.dp)
                                .height(200.dp)
                        )

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.padding(top = 60.dp)) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_star_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(23.dp),
                                    tint = Color(0xFFF3C623)
                                )
                                Text(
                                    text = "${s.vote_average}/10",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }

                            Text(
                                text = s.genres.joinToString(" | ") { it.name },
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = "Saisons: ${s.number_of_seasons}",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = "Episodes: ${s.number_of_episodes}",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = "Sortie le: ${s.first_air_date}",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.padding(bottom = 2.dp)
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
            item {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Têtes d'affiche",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA64D79),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 4.dp, start = 8.dp)
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
} else ->{
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(serie) { s ->
                    Box {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${s.backdrop_path}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize()
                                .height(300.dp)
                                .padding(top = 20.dp)
                        )

                        IconButton(
                            onClick = { navController.navigateUp() },
                            modifier = Modifier
                                .padding( start = 80.dp, top = 15.dp)
                                .align(Alignment.TopStart)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_keyboard_arrow_left_24),
                                contentDescription = "Back",
                                modifier = Modifier.size(40.dp),
                                tint = Color(0xFFD4BEE4)
                            )
                        }

                        Column(modifier = Modifier.padding(start = 220.dp, top = 260.dp)) {
                            Text(
                                text = s.name,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 25.sp,

                                )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .padding(start = 90.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${s.poster_path}",
                                contentDescription = null,
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(200.dp)
                            )

                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start,
                                    modifier = Modifier.padding(top = 100.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_star_24),
                                        contentDescription = null,
                                        modifier = Modifier.size(23.dp),
                                        tint = Color(0xFFF3C623)
                                    )
                                    Text(
                                        text = "${s.vote_average}/10",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 4.dp)
                                    )
                                }

                                Text(
                                    text = s.genres.joinToString(" | ") { it.name },
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "Saisons: ${s.number_of_seasons}",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "Episodes: ${s.number_of_episodes}",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    text = "Sortie le: ${s.first_air_date}",
                                    style = MaterialTheme.typography.titleSmall,
                                    modifier = Modifier.padding(bottom = 2.dp)
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
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Têtes d'affiche",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA64D79),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 4.dp, start = 90.dp)
                    )
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding( start = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        cast.chunked(3).forEach { rowCast ->
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
                                            modifier = Modifier.padding(start = 40.dp)
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
    }
}
