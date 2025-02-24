package edu.mirea.onebeattrue.googlecast.di

import android.content.Context
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.SessionManager
import dagger.Module
import dagger.Provides

@Module
interface CastModule {

    companion object {
        @[ApplicationScope Provides]
        fun provideCastContext(
            context: Context,
        ): CastContext = CastContext.getSharedInstance(context)

        @[ApplicationScope Provides]
        fun provideSessionManager(
            castContext: CastContext,
        ): SessionManager = castContext.sessionManager
    }
}