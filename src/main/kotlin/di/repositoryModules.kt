package di

import data.repositories.*
import domain.repositories.*
import org.koin.dsl.module

val repositoryModules = module {
    single<RedisRepository> { RedisRepositoryImpl(get()) }
    single<EncryptionRepository> { EncryptionRepositoryImpl() }
    single<AiChatRepository> { GroqRepositoryImpl(get(),get()) }
    single<UserRepository> { UserRepositoryImpl(get() , get(), get()) }
    single<ChatHistoryRepository> { ChatHistoryHistoryRepositoryImpl(get()) }
}