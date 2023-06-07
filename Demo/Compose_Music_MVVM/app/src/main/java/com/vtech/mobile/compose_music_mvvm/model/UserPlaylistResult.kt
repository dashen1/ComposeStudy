package com.vtech.mobile.compose_music_mvvm.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.android.exoplayer2.extractor.mp4.Track
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.concurrent.Flow


/**
 * 个人歌单
 */
@Keep
data class UserPlaylistResult(
    val playlist: List<PlaylistBean>
) : BaseResult()

@Keep
@Parcelize
data class PlaylistBean(
    val tracks: List<Track>?,
    val trackId: List<TrackId>?,
    val creator: Subscribers,
    val name: String = "",
    val coverImgUrl: String = "",
    val trackCount: Int = 0,
    val id: Long = 0,
    val playCount: Long = 0,
    val description: String?,
    val shareCount: Int,
    val commentCount: Int
) : Parcelable

@Keep
data class Subscribers(
    val userId: Long,
    val avatarUrl: String,
    val nickName: String
) : Serializable

@Keep
data class Track(
    val name: String,
    val id: Long,
    val mv: Long,
    val ar: List<Ar>,
    val al: Al
) : Serializable

@Keep
data class TrackId(
    val id: Int = 0,
    val v: Int = 0,
    val alg: String? = null
) : Serializable

@Keep
data class Ar(
    val id: Long,
    val name: String
)

@Keep
data class Al(
    val id: Long,
    val name: String,
    val picUrl: String
)