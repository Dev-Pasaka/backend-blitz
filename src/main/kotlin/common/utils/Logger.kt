package common.utils

private const val RESET = "\u001B[0m"
private const val BLUE = "\u001B[34m"  // DEBUG
private const val GREEN = "\u001B[32m" // INFO
private const val YELLOW = "\u001B[33m" // WARN
private const val RED = "\u001B[31m"  // ERROR / TRACE


infix fun String.logger(type: Type) {
    val coloredMessage = when (type) {
        Type.INFO -> "$GREEN$this$RESET"
        Type.WARN -> "$YELLOW$this$RESET"
        Type.DEBUG -> "$BLUE$this$RESET"
        Type.TRACE -> "$RED$this$RESET"
    }

    when (type) {
        Type.INFO -> ServerConfig.logger.info(coloredMessage)
        Type.WARN -> ServerConfig.logger.warn(coloredMessage)
        Type.DEBUG -> ServerConfig.logger.debug(coloredMessage)
        Type.TRACE -> ServerConfig.logger.trace(coloredMessage)
    }
}

enum class Type {
    INFO,
    WARN,
    DEBUG,
    TRACE
}