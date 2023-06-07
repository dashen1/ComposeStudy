package com.vtech.mobile.compose_music_mvvm.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.gson.Gson
import com.vtech.mobile.compose_music_mvvm.model.PlaylistBean
import com.vtech.mobile.compose_music_mvvm.model.SongBean
import com.vtech.mobile.compose_music_mvvm.model.Video

/**
 * 单例类
 */
object MusicNavController {
    lateinit var instance: NavHostController
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MusicNavGraph(
    navHostController: NavHostController,
    startDestination: String = RouterUrls.SPLASH,
    onFinish: () -> Unit = {}
) {
    MusicNavController.instance = navHostController
    AnimatedNavHost(navController = navHostController, startDestination = startDestination) {
        composable(RouterUrls.SPLASH) {
            SplashPage()
        }
        composable(RouterUrls.LOGIN) {
            QrcodeLoginPage()
        }
        composable(RouterUrls.HOME) {
            HomePage()
        }
        composable(RouterUrls.PROFILE,
            enterTransition = { EnterTransition.None }) {
            ProfilePage()
        }
        composable("${RouterUrls.PLAY_LIST}/{${RouterKV.PLAY_LIST_BEAN}}") {
            val playlistBeanJson = it.arguments?.getString(RouterKV.SONG_BEAN)!!
            val playlistBean = Gson().fromJson(playlistBeanJson,PlaylistBean::class.java)
            PlaylistPager(playlistBean)
        }
        composable("${RouterUrls.SONG_COMMENT}/{${RouterKV.SONG_BEAN}}") {
            val songBeanJson = it.arguments?.getString(RouterKV.SONG_BEAN)!!
            val songBean = Gson().fromJson(songBeanJson, SongBean::class.java)
            SongCommentPage(songBean)
        }
        composable("${RouterUrls.PLAY_VIDEO}/{${RouterKV.VIDEO_BEAN}}/{${RouterKV.VIDEO_GROUP_ID}}/{${RouterKV.VIDEO_OFFSET_INDEX}}",
        arguments = listOf(
            navArgument(RouterKV.VIDEO_BEAN){type = NavType.StringType},
            navArgument(RouterKV.VIDEO_GROUP_ID){type = NavType.IntType},
            navArgument(RouterKV.VIDEO_OFFSET_INDEX){type = NavType.IntType},
        )){
            val videoBeanJson = it.arguments?.getString(RouterKV.VIDEO_BEAN)!!
            val videoGroupId = it.arguments?.getInt(RouterKV.VIDEO_GROUP_ID)!!
            val videoOffsetIndex = it.arguments?.getInt(RouterKV.VIDEO_OFFSET_INDEX)!!
            val videoBean = Gson().fromJson(videoBeanJson, Video::class.java)
            PlayVideoPage(videoBean, videoGroupId, videoOffsetIndex)
        }
    }
}
