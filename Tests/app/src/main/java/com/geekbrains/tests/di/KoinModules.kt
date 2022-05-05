package com.geekbrains.tests.di

import com.geekbrains.tests.presenter.RepositoryContract
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.presenter.details.PresenterDetailsContract
import com.geekbrains.tests.presenter.search.PresenterSearchContract
import com.geekbrains.tests.presenter.search.SearchPresenter
import com.geekbrains.tests.repository.GitHubRepository
import org.koin.dsl.module

val appModule = module {
    single { NetworkModule.getOkHttpClient() }
    single { NetworkModule.getRetrofit(okHttpClient = get()) }
    single { NetworkModule.getGithubApiService(retrofit = get()) }

    // Repositories
    single<RepositoryContract> { GitHubRepository(gitHubApi = get()) }

    // Presenters
    factory<PresenterSearchContract> { SearchPresenter(repository = get()) }
    factory<PresenterDetailsContract> { DetailsPresenter() }
}