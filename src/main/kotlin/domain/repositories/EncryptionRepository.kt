package domain.repositories



interface EncryptionRepository {
    fun hashText(text: String): String
    fun verifyHashCode(text: String, hashedText: String): Boolean
    fun generateOtpCode(): String
}