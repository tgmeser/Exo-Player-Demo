package com.babyapps.exoplayerdemo

import android.net.Uri

interface MetaDataReader {
    fun getMetaDataFromUri(uri: Uri): MetaData?
}
