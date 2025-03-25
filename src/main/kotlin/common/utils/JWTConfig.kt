import com.typesafe.config.ConfigFactory

object JWTConfig {
    private val load = ConfigFactory.load()
    val jwtAudience = load.getString("ktor.jwt.audience") ?: ""
    val jwtDomain = load.getString("ktor.jwt.domain") ?: ""
    val jwtIssuer = load.getString("ktor.jwt.issuer") ?: ""
    val jwtRealm:String = load.getString("ktor.jwt.realm") ?: ""
    val jwtSecret = System.getenv("JWT_SECRETE") ?: ""
}