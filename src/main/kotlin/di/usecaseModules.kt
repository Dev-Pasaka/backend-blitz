package di

import domain.usecase.user.CreateUserUseCase
import org.koin.dsl.module

val  useCaseModules = module{
    single { CreateUserUseCase(get()) }
}