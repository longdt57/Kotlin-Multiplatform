package leegroup.module.core

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.core.util.JsonUtil
import org.junit.Test

class JsonUtilTest {

    @Test
    fun `decodeFromString should return User for valid JSON`() {
        // Arrange
        val json = """{"id":1,"firstName":"John","lastName":"Doe"}"""

        // Act
        val result = JsonUtil.decodeFromString<TestUser>(json)

        // Assert
        assertNotNull(result)
        assertEquals(1, result?.id)
        assertEquals("John", result?.firstName)
        assertEquals("Doe", result?.lastName)
    }

    @Test
    fun `decodeFromString should return null for invalid JSON`() {
        // Arrange
        val invalidJson = """{"id":1,"firstName":"John","lastName":}"""

        // Act
        val result = JsonUtil.decodeFromString<TestUser>(invalidJson)

        // Assert
        assertNull(result)
    }

    @Test
    fun `decodeFromString should return null for mismatched JSON structure`() {
        // Arrange
        val json = """{"name":"John","age":30}"""

        // Act
        val result = JsonUtil.decodeFromString<TestUser>(json)

        // Assert
        assertNull(result)
    }

    @Test
    fun `encodeToString should return valid JSON for User`() {
        // Arrange
        val user = TestUser(1, "Jane", "Doe")

        // Act
        val result = JsonUtil.encodeToString(user)

        // Assert
        assertEquals("""{"id":1,"firstName":"Jane","lastName":"Doe"}""", result)
    }

    @Serializable
    data class TestUser(
        @SerialName("id")
        val id: Int,

        @SerialName("firstName")
        val firstName: String,

        @SerialName("lastName")
        val lastName: String
    )
}

