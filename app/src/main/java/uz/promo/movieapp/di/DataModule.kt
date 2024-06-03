package uz.promo.movieapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.promo.movieapp.data.repository.NetworkRepositoryImpl
import uz.promo.movieapp.domain.repository.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository
}