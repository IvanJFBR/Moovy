package com.interview.ivanjfbr.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil3.request.ImageRequest
import com.interview.ivanjfbr.core.commons.Constants.TMDB_IMAGE_URL

@Composable
fun getImageRequest(imageUrl: String) = ImageRequest.Builder(LocalContext.current)
    .data(imageUrl)
    .build()

@Composable
fun getTMDBImageRequest(imageUrl: String) = ImageRequest.Builder(LocalContext.current)
    .data(TMDB_IMAGE_URL + imageUrl)
    .build()