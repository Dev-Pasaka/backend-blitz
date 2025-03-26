package di

import data.remote.dto.response.groqChatRes.GroqChatRes
import data.repositories.EncryptionRepositoryImpl
import data.repositories.GroqRepositoryImpl
import data.repositories.RedisRepositoryImpl
import data.repositories.UserRepositoryImpl
import domain.repositories.AiChatRepository
import domain.repositories.EncryptionRepository
import domain.repositories.RedisRepository
import domain.repositories.UserRepository
import org.koin.dsl.module

val repositoryModules = module {
    single<RedisRepository> { RedisRepositoryImpl(get()) }
    single<EncryptionRepository> { EncryptionRepositoryImpl() }
    single<AiChatRepository> { GroqRepositoryImpl(get(),get()) }
    single<UserRepository> { UserRepositoryImpl(get() , get(), get()) }
}