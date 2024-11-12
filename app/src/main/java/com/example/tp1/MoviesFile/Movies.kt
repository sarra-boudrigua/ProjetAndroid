package com.example.tp1.MoviesFile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.tp1.TopBar
import com.example.tp1.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun films(windowSizeClass: WindowSizeClass, viewModel: MainViewModel = viewModel(), navController: NavHostController) {
    val movies by viewModel.movies.collectAsState()
    val searchedMovies by viewModel.searchedMovies.collectAsState()
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getFilmsInitiaux()
        viewModel.observeSearchTextChanges()
    }

    fun updateSearchText(newText: String) {
        searchText = newText
        viewModel.search.value = newText
    }

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Scaffold(
                topBar = { TopBar(query = searchText, onchange = { updateSearchText(it) }) }
            ) { innerPadding ->
                Column {
                    val moviesAffichés = if (searchedMovies.isNotEmpty()) searchedMovies
                    else movies
                    if (moviesAffichés.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.padding(top = 70.dp, start = 20.dp, end = 20.dp)

                        ) {
                            items(moviesAffichés) { m ->
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
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
                                    Text(
                                        text = m.release_date,
                                        modifier = Modifier.padding(3.dp),
                                        fontSize = 20.sp,
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            text = "Chargement des films...",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }

        else -> {
//                 Scaffold (
//                     floatingActionButton = {
//                         FloatingActionButton(onClick = { },
//                             modifier = Modifier.padding(end = 16.dp, bottom = 72.dp)) {
//                             Icon(Icons.Default.Search, contentDescription = "Search")
//                         }
//                 }) { innerPadding ->

                         LazyVerticalGrid(
                             columns = GridCells.Fixed(3),
                             modifier = Modifier.padding(top = 35.dp, start = 75.dp, end = 20.dp),
                             horizontalArrangement = Arrangement.spacedBy(16.dp),
                             verticalArrangement = Arrangement.spacedBy(16.dp)
                         )
                         {
                             items(movies) { m ->
                                 Column {
                                     AsyncImage(
                                         model = "https://image.tmdb.org/t/p/w780" + m.poster_path,
                                         modifier = Modifier.size(300.dp)
                                             .clickable(onClick = {
                                                 navController.navigate("film/${m.id}")
                                             }),
                                         contentDescription = null,
                                     )
                                     Text(
                                         text = m.title,
                                         fontWeight = FontWeight.Bold,
                                         fontSize = 22.sp,
                                         modifier = Modifier.padding(start = 30.dp)
                                     )
                                     Text(text = m.release_date,
                                         modifier = Modifier.padding(start = 30.dp))
                                 }
                             }
                         }
                     }
                 }
        }

//}


