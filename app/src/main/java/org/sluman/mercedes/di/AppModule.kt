package org.sluman.mercedes.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sluman.mercedes.data.network.ApiClient
import org.sluman.mercedes.data.UserRepositoryImpl
import org.sluman.mercedes.domain.GetUserDetailsUseCase
import org.sluman.mercedes.domain.GetUsersUseCase
import org.sluman.mercedes.domain.UserRepository
import org.sluman.mercedes.domain.UserUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiClient(@ApplicationContext appContext: Context): ApiClient {
        return ApiClient(appContext)
    }

    @Provides
    @Singleton
    fun provideUserRepository(@ApplicationContext appContext: Context): UserRepository {
        return UserRepositoryImpl(ApiClient(appContext))
    }

    @Provides
    @Singleton
    fun provideUserUseCases(userRepository: UserRepository): UserUseCases {
        return UserUseCases(
            getUsersUseCase = GetUsersUseCase(userRepository),
            getUserDetailUseCase = GetUserDetailsUseCase(userRepository)
        )
    }
}