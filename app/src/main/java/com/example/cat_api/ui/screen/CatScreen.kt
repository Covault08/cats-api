package com.example.cat_api.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cat_api.R
import com.example.cat_api.data.model.CatsModelItem
import com.example.cat_api.domain.UiState
import com.example.cat_api.viewModel.CatsViewModel

@Composable
fun CatScreen(
    viewModel: CatsViewModel,
    modifier: Modifier
){
    val catsState = viewModel.catsState.collectAsStateWithLifecycle().value
    when(catsState){
        is UiState.ERROR -> {
            catsState.e.localizedMessage?.let { Log.e("CatScreen", it) }
        }
        is UiState.LOADING -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                CircularProgressIndicator()
            }
        }
        is UiState.SUCCESS -> {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(catsState.response.size){
                    Item(catsState.response.get(it), modifier)
                }
            }
        }
    }
}

@Composable
fun Item(
    cat: CatsModelItem,
    modifier: Modifier
){
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        cat.url.let{
            AsyncImage(
                contentDescription = cat.id,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it).error(R.drawable.baseline_broken_image_24).build()
            )
        }
        Text(text = cat.id)
    }
}
