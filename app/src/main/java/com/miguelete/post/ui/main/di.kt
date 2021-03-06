package com.miguelete.post.ui.main

import com.miguelete.domain.IPostRepository
import com.miguelete.usecases.GetPostListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getPostListProvider(postRepository: IPostRepository) =
        GetPostListUseCase(postRepository)

}
