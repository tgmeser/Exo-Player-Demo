package com.babyapps.exoplayerdemo

import android.net.Uri
import androidx.media3.common.MediaItem

data class VideoItem(val uri: Uri, val item: MediaItem, val name: String)
