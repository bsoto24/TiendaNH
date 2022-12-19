package com.sunafil.mitienda.feature.products.di

import com.sunafil.mitienda.feature.products.domain.ProductoRepository
import com.sunafil.mitienda.feature.products.data.ProductoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * RepositoryModule
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 19/12/22
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductoRepository(
        productoRepository: ProductoRepositoryImpl
    ): ProductoRepository

}