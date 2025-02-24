package edu.mirea.onebeattrue.googlecast.data

import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.framework.SessionManager
import edu.mirea.onebeattrue.googlecast.domain.CastRepository
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager,
) : CastRepository {

    override fun playVideo(videoUrl: String) {
        val currentSession = sessionManager.currentCastSession
        val remoteMediaClient = currentSession?.remoteMediaClient

        remoteMediaClient?.let { client ->
            val mediaMetadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE).apply {
                putString(MediaMetadata.KEY_TITLE, VIDEO_TITLE)
            }

            val mediaInfo = MediaInfo.Builder(videoUrl)
                .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                .setContentType(TYPE_MP4)
                .setMetadata(mediaMetadata)
                .build()

            val mediaLoadRequestData = MediaLoadRequestData.Builder()
                .setMediaInfo(mediaInfo)
                .build()

            client.load(mediaLoadRequestData)
        } ?: throw RuntimeException(ERROR_NO_ACTIVE_SESSION)
    }

    companion object {
        private const val TYPE_MP4 = "video/mp4"
        private const val VIDEO_TITLE = "Видео"
        private const val ERROR_NO_ACTIVE_SESSION = "Отсутствует активная Cast-сессия"
    }
}