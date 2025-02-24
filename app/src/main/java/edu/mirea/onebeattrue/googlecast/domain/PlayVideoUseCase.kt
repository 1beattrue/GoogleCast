package edu.mirea.onebeattrue.googlecast.domain

import javax.inject.Inject

class PlayVideoUseCase @Inject constructor(
    private val repository: CastRepository,
) {
    operator fun invoke(videoUrl: String) {
        repository.playVideo(videoUrl)
    }
}