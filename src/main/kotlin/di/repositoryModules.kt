package di

import data.repositories.RedisRepositoryImpl
import domain.repositories.RedisRepository
import org.koin.dsl.module

val repositoryModules = module {
    single<RedisRepository> { RedisRepositoryImpl(get()) }
}