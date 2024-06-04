package org.sluman.mercedes

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sluman.mercedes.data.network.ApiClient
import org.sluman.mercedes.data.UserRepositoryImpl
import org.sluman.mercedes.domain.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        return ApiClient
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(ApiClient)
    }
}