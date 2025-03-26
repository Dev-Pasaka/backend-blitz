package application.plugins

import di.configModules
import di.repositoryModules
import di.serviceModules
import di.useCaseModules
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(
            configModules,
            repositoryModules,
            useCaseModules,
            serviceModules
        )
    }
}
