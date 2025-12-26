package leegroup.module.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.core.util.JsonUtil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

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

    @Test
    fun `encodeToMap should return valid Map for User`() {
        // Arrange
        val user = TestUser(1, "Jane", "Doe")

        // Act
        val result = JsonUtil.encodeToMap(user)

        // Assert
        assertEquals(3, result.size)
        assertEquals("1", result["id"])
        assertEquals("Jane", result["firstName"])
        assertEquals("Doe", result["lastName"])
    }

    @Test
    fun `decodeFromMap should return User for valid Map`() {
        // Arrange
        val map = mapOf(
            "id" to 1,
            "firstName" to "John",
            "lastName" to "Doe"
        )

        // Act
        val result = JsonUtil.decodeFromMap<TestUser>(map)

        // Assert
        assertNotNull(result)
        assertEquals(1, result?.id)
        assertEquals("John", result?.firstName)
        assertEquals("Doe", result?.lastName)
    }

    @Test
    fun `decodeFromMap should return null for invalid Map structure`() {
        // Arrange
        val map = mapOf(
            "name" to "John",
            "age" to 30
        )

        // Act
        val result = JsonUtil.decodeFromMap<TestUser>(map)

        // Assert
        assertNull(result)
    }

    @Test
    fun `encodeToMap and decodeFromMap should be reversible`() {
        // Arrange
        val originalUser = TestUser(42, "Alice", "Smith")

        // Act
        val map = JsonUtil.encodeToMap(originalUser)
        val decodedUser = JsonUtil.decodeFromMap<TestUser>(map)

        // Assert
        assertNotNull(decodedUser)
        assertEquals(originalUser.id, decodedUser?.id)
        assertEquals(originalUser.firstName, decodedUser?.firstName)
        assertEquals(originalUser.lastName, decodedUser?.lastName)
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