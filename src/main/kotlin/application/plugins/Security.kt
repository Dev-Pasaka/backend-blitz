package application.plugins

import JWTConfig
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import common.utils.Type
import common.utils.logger
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {
    val jwtAudience = JWTConfig.jwtAudience
    val jwtIssuer = JWTConfig.jwtIssuer
    val jwtRealm = JWTConfig.jwtRealm
    val jwtSecret = JWTConfig.jwtSecret
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtIssuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}


fun ApplicationCall.extractUserId(): String {
    val id =  principal<JWTPrincipal>()
        ?.payload
        ?.getClaim("id")
        ?.asString()
        ?.removeSurrounding("\"")
        ?: ""
    return id

}