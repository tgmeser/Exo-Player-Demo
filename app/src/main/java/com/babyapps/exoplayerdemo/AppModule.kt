package com.babyapps.exoplayerdemo

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @ViewModelScoped
    @Provides
    fun provideVideoPlayer(app: Application): Player = ExoPlayer.Builder(app).build()

    @ViewModelScoped
    @Provides
    fun provideMetaDataReader(app: Application): MetaDataReader = MetaDataReaderImpl(app)
}