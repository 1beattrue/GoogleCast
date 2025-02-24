package edu.mirea.onebeattrue.googlecast.domain

interface CastRepository {
    fun playVideo(videoUrl: String)
}