package edu.mirea.onebeattrue.googlecast.di

import android.content.Context
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.SessionManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import edu.mirea.onebeattrue.googlecast.data.CastRepositoryImpl
import edu.mirea.onebeattrue.googlecast.domain.CastRepository

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindCastRepository(impl: CastRepositoryImpl): CastRepository

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