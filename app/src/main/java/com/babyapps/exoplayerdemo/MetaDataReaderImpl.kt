package com.babyapps.exoplayerdemo

import android.app.Application
import android.net.Uri
import android.provider.MediaStore

class MetaDataReaderImpl(val app: Application) : MetaDataReader {
    override fun getMetaDataFromUri(uri: Uri): MetaData? {
        if (uri.scheme != "content") {
            return null
        }
        val fileName = app.contentResolver.query(
            uri,
            arrayOf(MediaStore.Video.VideoColumns.DISPLAY_NAME),
            null,
            null
        )?.use {
            val index = it.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
            it.moveToFirst()
            it.getString(index)
        }
        return fileName?.let { MetaData(fileName = Uri.parse(it).lastPathSegment ?: return null) }
    }

}