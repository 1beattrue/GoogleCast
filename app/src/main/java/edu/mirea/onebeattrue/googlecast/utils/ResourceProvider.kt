package edu.mirea.onebeattrue.googlecast.utils

import android.content.Context
import androidx.annotation.StringRes
import edu.mirea.onebeattrue.googlecast.di.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class ResourceProvider @Inject constructor(
    private val context: Context,
) {
    fun getString(@StringRes resId: Int): String = context.getString(resId)
    fun getString(@StringRes resId: Int, vararg args: Any): String = context.getString(resId, *args)
}