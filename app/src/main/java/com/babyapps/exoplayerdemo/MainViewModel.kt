package com.babyapps.exoplayerdemo

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
    val metaDataReader: MetaDataReader
) :
    ViewModel() {
    private val videoUris = savedStateHandle.getStateFlow("videoUris", emptyList<Uri>())

    val videoItems = videoUris.map { uriList ->
        uriList.map { uri ->
            VideoItem(
                uri = uri,
                item = MediaItem.fromUri(uri),
                name = metaDataReader.getMetaDataFromUri(uri)?.fileName ?: "Unknown"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    init {
        player.prepare()
    }

    fun addVideoUri(uri: Uri) {
        // Adding new video to play list
        savedStateHandle["videoUris"] = videoUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun clickToPlayVideo(uri: Uri) {
        player.setMediaItem(videoItems.value.find { it.uri == uri }?.item ?: return)
    }

    override fun onCleared() {
        super.onCleared()
        // Leaving resources when viewmodel ends
        player.release()
    }


}