package com.example.tp1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel() : ViewModel() {
    //pour stockage
    private val _movies = MutableStateFlow<List<moviesResult>>(emptyList())
    val movies: StateFlow<List<moviesResult>> = _movies

    private val _searchedMovies = MutableStateFlow<List<moviesResult>>(emptyList())
    val searchedMovies: StateFlow<List<moviesResult>> = _searchedMovies

    private val _searchedSeries = MutableStateFlow<List<seriesResult>>(emptyList())
    val searchedSeries: StateFlow<List<seriesResult>> = _searchedSeries

    private val _series = MutableStateFlow<List<seriesResult>>(emptyList())
    val series: StateFlow<List<seriesResult>> = _series

    private val _acteurs = MutableStateFlow<List<personResult>>(emptyList())
    val acteurs: StateFlow<List<personResult>> = _acteurs

    private val _searchedActeurs = MutableStateFlow<List<personResult>>(emptyList())
    val searchedActeurs: StateFlow<List<personResult>> = _searchedActeurs

    private val _movieDetails = MutableStateFlow<List<MovieDetailsModel>>(emptyList())
    val movieDetails: StateFlow<List<MovieDetailsModel>> = _movieDetails

    private val _serieDetails = MutableStateFlow<List<serieDetailsModel>>(emptyList())
    val serieDetails: StateFlow<List<serieDetailsModel>> = _serieDetails

    private val _movieCast = MutableStateFlow<List<Cast>>(emptyList())
    val movieCast: StateFlow<List<Cast>> = _movieCast

    private val _serieCast = MutableStateFlow<List<Cast>>(emptyList())
    val serieCast: StateFlow<List<Cast>> = _serieCast

    private val _serieDeActeur = MutableStateFlow<List<Cast2>>(emptyList())
    val serieDeActeur: StateFlow<List<Cast2>> = _serieDeActeur

    private val _movieDeActeur = MutableStateFlow<List<Cast1>>(emptyList())
    val movieDeActeur: StateFlow<List<Cast1>> = _movieDeActeur

    private val _acteurInfo = MutableStateFlow<List<ActeurInfoModel>>(emptyList())
    val acteurInfo: StateFlow<List<ActeurInfoModel>> = _acteurInfo

    var search = MutableStateFlow("")

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api: Api = retrofit.create(Api::class.java)
    private val apiKey = "317519a83cc36ab9367ba50e5aa75b40"
    val langage = "fr";


    fun getFilmsInitiaux() {
        viewModelScope.launch {
            val response = api.lastmovies(apiKey, langage)
            _movies.value = response.results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            val response = api.lastseries(apiKey, langage)
            _series.value = response.results
        }
    }

    fun getActeurs() {
        viewModelScope.launch {
            val response = api.lastpersonnes(apiKey, langage)
            _acteurs.value = response.results
        }
    }

    fun getMovieById(id: String) {
        viewModelScope.launch {
            val response = api.movieDetails(id, apiKey, langage)
            _movieDetails.value = listOf(response)
        }
    }

    fun getSerieById(id: String) {
        viewModelScope.launch {
            val response = api.serieDetails(id, apiKey, langage)
            _serieDetails.value = listOf(response)
        }
    }

    fun searchMovies() {
        viewModelScope.launch {
           val response = api.searchMovies(apiKey, search.value, langage)
            _searchedMovies.value=response.results
        }
    }
    fun observeSearchTextChanges() {
        viewModelScope.launch {
            search.collect { text ->
                if (text.isNotEmpty()) {
                    searchMovies();
                    searchSeries();
                    searchActeurs()
                } else {
                    getFilmsInitiaux();
                    getSeries();
                    getActeurs()

                }
            }
        }
    }

    fun searchSeries() {
        viewModelScope.launch {
            val response = api.searchSeries(apiKey, search.value, langage)
            _searchedSeries.value=response.results
        }
    }


    fun searchActeurs() {
        viewModelScope.launch {
            val response = api.searchActeur(apiKey, search.value, langage)
            _searchedActeurs.value=response.results
        }
    }

    fun movieCast(id: String) {
        viewModelScope.launch {
            val response = api.ActeursMovie(id, apiKey, langage)
            _movieCast.value=response.cast
        }
    }

    fun serieCast(id: String) {
        viewModelScope.launch {
            val response = api.ActeursSerie(id, apiKey, langage)
            _serieCast.value=response.cast
        }
    }

    fun serieDeActeur(id: String) {
        viewModelScope.launch {
            val response = api.SeriesDeActeur(id, apiKey, langage)
            _serieDeActeur.value=response.cast
        }
    }

    fun moviesDeActeur(id: String) {
        viewModelScope.launch {
            val response = api.MoviesDeActeur(id, apiKey, langage)
            _movieDeActeur.value=response.cast
        }
    }

    fun acteurInfo(id: String) {
        viewModelScope.launch {
            val response = api.Acteur(id, apiKey, langage)
            _acteurInfo.value= listOf(response)
        }
    }

}