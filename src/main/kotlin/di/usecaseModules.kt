package di

import domain.usecase.chat.ChatUseCase
import domain.usecase.user.CreateUserUseCase
import domain.usecase.user.GetUserProfileUserCase
import domain.usecase.user.SignInUserUseCase
import domain.usecase.user.UpdateUserUseCase
import org.koin.dsl.module

val  useCaseModules = module{
    single { CreateUserUseCase(get()) }
    single { SignInUserUseCase(get()) }
    single { GetUserProfileUserCase(get()) }
    single { UpdateUserUseCase(get()) }
    single { ChatUseCase(get(), get()) }
}