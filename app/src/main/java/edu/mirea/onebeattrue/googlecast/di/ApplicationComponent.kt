package edu.mirea.onebeattrue.googlecast.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import edu.mirea.onebeattrue.googlecast.presentation.ViewModelFactory

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        PresentationModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}