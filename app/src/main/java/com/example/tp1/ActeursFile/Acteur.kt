package com.example.tp1.ActeursFile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun acteur (windowSizeClass: WindowSizeClass, viewModel: MainViewModel, navController: NavController) {
    val id = navController.currentBackStackEntry?.arguments?.getString("id")
    val movie by viewModel.movieDeActeur.collectAsState()
    val cast by viewModel.acteurInfo.collectAsState()

    if ( movie.isEmpty()&& cast.isEmpty()) {
        viewModel.acteurInfo(id.toString())
        viewModel.moviesDeActeur(id.toString())
    }
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(cast) { c ->
                        Box {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${c.profile_path}",
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxSize()
                                    .height(300.dp)
                                    .padding(top = 40.dp)
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
                                    modifier = Modifier.size(38.dp),
                                    tint = Color(0xFFD4BEE4)
                                )
                            }

                            Column(modifier = Modifier.padding(start = 120.dp, top = 320.dp)) {
                                Text(
                                    text = c.name,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFA64D79),
                                    fontSize = 25.sp,

                                    )
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Column(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Biography",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFA64D79),
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = c.biography,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = "Films de l'acteur",
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
                            movie.chunked(2).forEach { rowMovie ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    rowMovie.forEach { m ->
                                        Column(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .weight(1f)
                                        ) {
                                            AsyncImage(
                                                model = "https://image.tmdb.org/t/p/w500${m.poster_path}",
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .height(300.dp)
                                                    .fillMaxWidth()
                                                    .clickable(onClick = {
                                                        navController.navigate("film/${m.id}")
                                                    }),
                                            )
                                            Text(
                                                text = m.title,
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



        else ->{
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(cast) { c ->
                        Box {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${c.profile_path}",
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
                                    modifier = Modifier.size(35.dp),
                                    tint = Color(0xFFD4BEE4)
                                )
                            }

                            Column(modifier = Modifier.padding(start = 360.dp, top = 300.dp)) {
                                Text(
                                    text = c.name,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFA64D79),
                                    fontSize = 25.sp,

                                    )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .padding(start = 90.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Biography",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFA64D79),
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = c.biography,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = "Films de l'acteur",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFA64D79),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 4.dp, start = 90.dp)
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            movie.chunked(3).forEach { rowMovie ->
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding( start = 50.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    rowMovie.forEach { m ->
                                        Column(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .weight(1f)
                                        ) {
                                            AsyncImage(
                                                model = "https://image.tmdb.org/t/p/w500${m.poster_path}",
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .height(300.dp)
                                                    .fillMaxWidth()
                                                    .clickable(onClick = {
                                                        navController.navigate("film/${m.id}")
                                                    }),
                                            )
                                            Text(
                                                text = m.title,
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
    }
}