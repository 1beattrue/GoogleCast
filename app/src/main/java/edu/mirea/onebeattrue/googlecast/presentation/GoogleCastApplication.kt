package edu.mirea.onebeattrue.googlecast.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import edu.mirea.onebeattrue.googlecast.di.ApplicationComponent
import edu.mirea.onebeattrue.googlecast.di.DaggerApplicationComponent

class GoogleCastApplication : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

val applicationComponent: ApplicationComponent
    @Composable
    get() = (LocalContext.current.applicationContext as GoogleCastApplication).component