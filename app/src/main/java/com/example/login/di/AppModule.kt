package com.example.login.di

import android.content.Context
import android.content.res.Resources
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.login.data.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAccountRepository(): AccountRepository {
        return AccountRepository
    }

    //IMPLEMENTAR PARA INTERNACIONALIZAR STRINGS
    @Provides
    @Singleton
    fun provideStringResources(@ApplicationContext context: Context): Resources {
        return context.resources
    }

    /**
     * Método que provee el DataStore (api-valor) de la sessión
     */
    @Singleton
    @Provides
    fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { context.preferencesDataStoreFile(Session.DATA) })
    }
}