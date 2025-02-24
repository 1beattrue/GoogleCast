package edu.mirea.onebeattrue.googlecast.presentation

sealed interface State {
    data object Initial : State
    data object Success : State
    data class Error(val throwable: Throwable) : State
}