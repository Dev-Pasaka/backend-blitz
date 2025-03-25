package common.utils

infix fun String.logger(logger:Type){
    when(logger){
        Type.INFO -> ServerConfig.logger.info(this)
        Type.WARN -> ServerConfig.logger.warn(this)
        Type.DEBUG -> ServerConfig.logger.debug(this)
        Type.TRACE -> ServerConfig.logger.trace(this)
    }
}

enum class Type {
    INFO,
    WARN,
    DEBUG,
    TRACE
}