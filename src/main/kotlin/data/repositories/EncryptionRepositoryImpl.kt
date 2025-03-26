package data.repositories

import domain.repositories.EncryptionRepository
import org.mindrot.jbcrypt.BCrypt
import java.security.SecureRandom




class EncryptionRepositoryImpl() : EncryptionRepository {
    override fun hashText(text: String): String = BCrypt.hashpw(text, BCrypt.gensalt())
    override fun verifyHashCode(text: String, hashedText: String): Boolean = try {
        BCrypt.checkpw(text, hashedText)
    } catch (e: Exception) {
        false
    }

    override fun generateOtpCode(): String {
        val random = SecureRandom()
        val bytes = ByteArray(6)
        random.nextBytes(bytes)
        return  bytes.map { it.toInt() and 6 }.joinToString("")
    }

}