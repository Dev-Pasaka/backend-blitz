package di

import org.koin.dsl.module
import presentation.services.ChatService
import presentation.services.UserService

val serviceModules = module {
    single {
        UserService(
            get(),
            get(),
            get(),
            get()
        )
    }

    single {
        ChatService(
            get()
        )
    }
}