package com.vtech.mobile.composedouban.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vtech.mobile.composedouban.data.RankDetail
import com.vtech.mobile.composedouban.viewmodel.paged.MovieSource
import kotlinx.coroutines.flow.Flow

class RankViewModel : ViewModel() {
    val rankItems: Flow<PagingData<RankDetail>> =
        Pager(PagingConfig(pageSize = 10, prefetchDistance = 1)) {
            MovieSource()
        }.flow
}