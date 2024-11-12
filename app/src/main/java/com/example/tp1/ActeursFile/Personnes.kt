package com.example.tp1.ActeursFile
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
import androidx.compose.ui.layout.ContentScale
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
fun personnes(windowSizeClass: WindowSizeClass, viewModel: MainViewModel = viewModel(), navController: NavHostController) {
    val personnes by viewModel.acteurs.collectAsState()
    val searchedActeurs by viewModel.searchedActeurs.collectAsState()
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getActeurs()
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
                    val acteursAffichés =
                        if (searchedActeurs.isNotEmpty()) searchedActeurs else personnes
                    if (acteursAffichés.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.padding(top = 70.dp, start = 20.dp, end = 20.dp)

                        ) {
                            items(acteursAffichés) { p ->
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                ) {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w500${p.profile_path}",
                                        contentDescription = null,
                                        modifier = Modifier
                                            .height(300.dp)
                                            .fillMaxWidth()
                                            .clickable(onClick = {
                                                navController.navigate("acteur/${p.id}")
                                            }),
                                        contentScale = ContentScale.Fit
                                    )
                                    Text(
                                        text = p.name,
                                        fontWeight = FontWeight.Bold,

                                        )
                                }
                            }
                        }
                    }
                }
            }
        }

        else -> {
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                modifier = Modifier.padding(top = 50.dp, start = 90.dp, end = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp))

            {
                items(personnes) { p ->
                    Column {

                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${p.profile_path}",
                                contentDescription = null,
                                modifier = Modifier
                                    .height(300.dp)
                                    .fillMaxWidth()
                                    .clickable(onClick = {
                                        navController.navigate("acteur/${p.id}")
                                    }),
                            )
                            Text(
                                text = p.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 30.dp),
                                fontSize = 23.sp,
                            )
                        }
                    }
                }
            }
        }
    }
