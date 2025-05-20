package com.interview.ivanjfbr.core.ui.components

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> HandlePagingResult(
    movieItems: LazyPagingItems<T>,
) {
    movieItems.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                LoadingView()
            }
            error != null -> {
                ErrorView()
            }
            movieItems.itemCount < 1 -> {

            }
            else -> {

            }

        }
    }
}