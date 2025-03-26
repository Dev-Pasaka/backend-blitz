package di

import JWTConfig
import common.utils.*
import org.koin.dsl.module

val configModules = module {
    single { RedisConfig }
    single { MongoDBConfig }
    single { JWTConfig }
    single { GroqConfig }
    single { HttpClient }
    single { ServerConfig }
}