package com.vtech.mobile.compose_music_mvvm.model

import androidx.annotation.Keep

@Keep
data class SongDetailResult(val songs:List<SongBean>):BaseResult()

@Keep
data class SongBean(
    val id:Long,
    val name:String,
    val al:Al,
    val ar:List<Ar>
)

@Keep
data class SongUrlBean(val data:List<SongUrl>)

@Keep
data class SongUrl(val url:String)