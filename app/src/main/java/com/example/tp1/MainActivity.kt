package com.example.tp1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.tp1.ui.theme.TP1Theme
import com.example.tp1.ui.theme.acteur
import com.example.tp1.ui.theme.films
import com.example.tp1.ui.theme.home
import com.example.tp1.ui.theme.personnes
import com.example.tp1.ui.theme.series
import film
import kotlinx.serialization.Serializable
import serie

@Serializable
class Home
@Serializable
class Films
@Serializable
class Serie
@Serializable
class Personnes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TP1Theme {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
//                    topBar = {
//                        if (currentDestination?.route != "home") {
//                            TopBar( )
//                        }
//                    },
                    bottomBar = {
                        if (currentDestination?.route != "home") {
                            BottomBar(navController, currentDestination, windowSizeClass)
                        }
                    },
                    floatingActionButton = {
                        if (currentDestination?.route != "home" &&windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT) {
                            FloatingActionButton(
                                onClick = { },
                            ) {
                                Icon(
                                    painterResource(R.drawable.baseline_search_24),
                                    contentDescription = "Search",
                                    Modifier.size(35.dp)
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
                        Nav(navController, innerPadding)
                    }
                else{
                Nav(navController, innerPadding)
                if (currentDestination?.route != "home") {
                    VerticalBar(navController)
                }
            }
            }
        }
    }
}
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(query: String, onchange: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isExpanded) {
                TextField(
                    value = query,
                    onValueChange = onchange,
                    placeholder = { Text("Recherche...") },
                    modifier = Modifier.fillMaxWidth(),
                )
            } else {
                Text("Mini_Projet")
            }
        },
        actions = {
            IconButton(onClick = {
                isExpanded = !isExpanded
            }) {
                Icon(
                    painterResource(R.drawable.baseline_search_24),
                    contentDescription = "Search",
                    Modifier.size(35.dp)
                )
            }

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFFD4BEE4),
        )
    )
}

@Composable
fun BottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    windowSizeClass: WindowSizeClass
) {
    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        NavigationBar(containerColor = Color(0xFFD4BEE4)) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(R.drawable.baseline_video_library_24),
                        contentDescription = null,
                        Modifier.size(30.dp)
                    )
                },
                label = { Text("Films") },
                selected = currentDestination?.route == "films",
                onClick = { navController.navigate("films") }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(R.drawable.baseline_tv_24),
                        contentDescription = null,
                        Modifier.size(30.dp)
                    )
                },
                label = { Text("Series") },
                selected = currentDestination?.route == "series",
                onClick = { navController.navigate("series") }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(R.drawable.baseline_person_24),
                        contentDescription = null,
                        Modifier.size(30.dp)
                    )
                },
                label = { Text("Acteurs") },
                selected = currentDestination?.route == "personnes",
                onClick = { navController.navigate("personnes") }
            )
        }
    }
}

    @Composable
    fun VerticalBar(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp)
                .background(Color(0xFFD4BEE4)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { navController.navigate("films") },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_video_library_24),
                        contentDescription = null
                    )
                }
                Text(
                    "Films",
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { navController.navigate("series") },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_tv_24),
                        contentDescription = null
                    )
                }
                Text(
                    "Series",
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { navController.navigate("personnes") },
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_person_24),
                        contentDescription = null
                    )
                }
                Text(
                    "Acteurs",
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }


@Composable
fun Nav(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            home(
                windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
                Modifier.padding(innerPadding),
                navController
            )
        }
        composable("films") {
            films(
                windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
                viewModel = viewModel(),
                navController
            )
        }
        composable("series") {
            series(
                windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
                viewModel = viewModel(),
                navController
            )
        }
        composable("personnes") { personnes(windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,viewModel = viewModel(),navController) }

        composable("film/{id}") {
            film(
                windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
                viewModel = viewModel(),
                navController
            )
        }
        composable("serie/{id}") {
            serie(
                windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
                viewModel = viewModel(),
                navController
            )
        }
        composable("acteur/{id}") { acteur( windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass, viewModel = viewModel(), navController) } }
}

