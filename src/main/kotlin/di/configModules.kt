package di

import JWTConfig
import common.utils.MongoDBConfig
import common.utils.RedisConfig
import org.koin.dsl.module

val configModules = module {
    single { RedisConfig }
    single { MongoDBConfig }
    single { JWTConfig }
}