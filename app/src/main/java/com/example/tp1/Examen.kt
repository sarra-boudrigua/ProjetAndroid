package com.example.tp1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun examenScreen (viewModel: MainViewModel = viewModel()) {
    val collection by viewModel.collection.collectAsState()
    if (collection.isEmpty()) {
        viewModel.searchCollection()
    }

    Column(modifier = Modifier.padding(top=60.dp, start = 10.dp)) {
Row {
        Text(
            text = "Fav'app",
            color = Color(0xFFA64D79),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,

        )
    Spacer(modifier = Modifier.weight(1f))//Pour éloigner l'icone
    Icon(
            painter = painterResource(R.drawable.baseline_search_24),
            contentDescription = null,
            modifier = Modifier.size(35.dp),
            tint = Color.Black
        )
}
        LazyVerticalGrid (
                    columns = GridCells.Fixed(2),
        ) {
        items(collection) { c ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${c.poster_path}",
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
