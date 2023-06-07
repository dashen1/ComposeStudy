package com.vtech.mobile.compose_music_mvvm.model

import androidx.annotation.Keep

@Keep
data class VideoGroupResult(
    val datas: List<VideoBean>
) : BaseResult()

@Keep
data class VideoBean(
    val type: Int,
    val displayed: Boolean,
    val alg: String?,
    val data: Video
)

@Keep
data class Video(
    val scm: String?,
    val coverUrl: String?,
    val height: Int,
    val width: Int,
    val title: String?,
    val description: String,
    val commentCount: Int,
    val shareCount: Int,
    val creator: Subscribers?,
    val previewUrl: String,
    val relateSong: List<SongBean>?,
    val vid: String?,
    val durationms: Int,
    val playTime: Int,
    val praisedCount: Int,
    var urls: List<VideoUrlBean>? = null
) {

    companion object {
        val coverHeightMap = hashMapOf<String, Int>()

        fun getCoverHeight(vid: String) : Int {
            return coverHeightMap.getOrPut(vid) {
                (300..550).random()
            }
        }
    }
}