package com.interview.ivanjfbr.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class MoviesPagingSource<T : Any>(
    private val fetch: suspend (page: Int) -> List<T>
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val position = params.key ?: 1
        return try {
            val items = fetch(position)
            LoadResult.Page(
                data = items,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (items.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? =
        state.anchorPosition
}