package edu.mirea.onebeattrue.googlecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.mirea.onebeattrue.googlecast.domain.PlayVideoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val playVideoUseCase: PlayVideoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Initial)
    val state: StateFlow<State> = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = State.Error(throwable)
    }

    fun castVideo(videoUrl: String) {
        viewModelScope.launch(exceptionHandler) {
            playVideoUseCase(videoUrl)
            _state.value = State.Success
        }
    }
}