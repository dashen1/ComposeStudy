package com.vtech.mobile.composedouban.viewmodel.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vtech.mobile.composedouban.data.RankDetail
import com.vtech.mobile.composedouban.data.rankDetailList
import kotlinx.coroutines.delay

class MovieSource : PagingSource<Int, RankDetail>() {
    override fun getRefreshKey(state: PagingState<Int, RankDetail>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RankDetail> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = rankDetailList
            if (nextPage > 1) {
                delay(2000)
            }
            LoadResult.Page(
                data = movieListResponse,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}