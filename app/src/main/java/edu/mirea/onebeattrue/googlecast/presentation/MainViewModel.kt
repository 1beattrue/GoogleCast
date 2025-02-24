package edu.mirea.onebeattrue.googlecast.presentation

import androidx.lifecycle.ViewModel
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManager
import edu.mirea.onebeattrue.googlecast.R
import edu.mirea.onebeattrue.googlecast.utils.BaseSessionManagerListener
import edu.mirea.onebeattrue.googlecast.utils.ResourceProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val sessionListener = object : BaseSessionManagerListener() {
        override fun onSessionStarted(session: CastSession, sessionId: String) {
            playVideo(session)
        }

        override fun onSessionResumed(session: CastSession, wasSuspended: Boolean) {
            playVideo(session)
        }
    }

    init {
        sessionManager.addSessionManagerListener(sessionListener, CastSession::class.java)
    }

    private fun playVideo(
        session: CastSession,
    ) {
        val videoUrl = resourceProvider.getString(R.string.video_url)
        val videoTitle = resourceProvider.getString(R.string.video_title)

        val remoteMediaClient = session.remoteMediaClient
        remoteMediaClient?.let { client ->
            val mediaMetadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE).apply {
                putString(MediaMetadata.KEY_TITLE, videoTitle)
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
        }
    }

    companion object {
        private const val TYPE_MP4 = "video/mp4"
    }
}