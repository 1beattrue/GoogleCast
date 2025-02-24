package edu.mirea.onebeattrue.googlecast.utils

import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener

abstract class BaseSessionManagerListener : SessionManagerListener<CastSession> {
    override fun onSessionStarting(session: CastSession) {}
    override fun onSessionSuspended(session: CastSession, reason: Int) {}
    override fun onSessionEnded(session: CastSession, error: Int) {}
    override fun onSessionEnding(session: CastSession) {}
    override fun onSessionResumeFailed(session: CastSession, error: Int) {}
    override fun onSessionStartFailed(session: CastSession, error: Int) {}
    override fun onSessionResuming(session: CastSession, sessionId: String) {}
}