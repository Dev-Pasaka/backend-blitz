package di

import domain.usecase.chat.*
import domain.usecase.user.CreateUserUseCase
import domain.usecase.user.GetUserProfileUserCase
import domain.usecase.user.SignInUserUseCase
import domain.usecase.user.UpdateUserUseCase
import org.koin.dsl.module

val  useCaseModules = module{
    // User
    single { CreateUserUseCase(get()) }
    single { SignInUserUseCase(get()) }
    single { GetUserProfileUserCase(get()) }
    single { UpdateUserUseCase(get()) }
    // Chats History
    single { ChatUseCase(get(), get()) }
    single { GetAllChatsHistoryUseCase(get()) }
    single { GetChatHistoryUseCase(get()) }
    single { DeleteAllChatHistoryUseCase(get()) }
    single { DeleteChatHistoryUseCase(get()) }
}