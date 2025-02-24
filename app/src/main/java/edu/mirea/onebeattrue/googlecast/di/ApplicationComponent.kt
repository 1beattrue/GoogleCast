package edu.mirea.onebeattrue.googlecast.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import edu.mirea.onebeattrue.googlecast.presentation.ViewModelFactory

@ApplicationScope
@Component(
    modules = [
        CastModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    val viewModelFactory: ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}