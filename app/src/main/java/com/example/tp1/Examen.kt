package com.example.tp1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun examenScreen (viewModel: MainViewModel = viewModel()){
    val collection by viewModel.collection.collectAsState()
    if (collection.isEmpty()) {
        viewModel.searchCollection()
    }
    LazyColumn {
        items (collection) { c ->
            Text(text = c.name)
        }
    }

}