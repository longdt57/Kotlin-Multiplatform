package leegroup.module.gituser.data.models

import kotlin.test.Test
import kotlin.test.assertEquals

class GitUserMapperTest {

    @Test
    fun `test GitUser mapToDomain maps all fields correctly`() {
        // Given
        val gitUser = GitUser(
            id = 12345L,
            login = "testuser",
            avatarUrl = "https://example.com/avatar.jpg",
            htmlUrl = "https://github.com/testuser"
        )

        // When
        val result = gitUser.mapToDomain()

        // Then
        assertEquals(12345L, result.id)
        assertEquals("testuser", result.login)
        assertEquals("https://example.com/avatar.jpg", result.avatarUrl)
        assertEquals("https://github.com/testuser", result.htmlUrl)
    }

    @Test
    fun `test GitUser mapToDomain with null avatarUrl`() {
        // Given
        val gitUser = GitUser(
            id = 12345L,
            login = "testuser",
            avatarUrl = null,
            htmlUrl = "https://github.com/testuser"
        )

        // When
        val result = gitUser.mapToDomain()

        // Then
        assertEquals(null, result.avatarUrl)
    }

    @Test
    fun `test GitUser mapToDomain with null htmlUrl`() {
        // Given
        val gitUser = GitUser(
            id = 12345L,
            login = "testuser",
            avatarUrl = "https://example.com/avatar.jpg",
            htmlUrl = null
        )

        // When
        val result = gitUser.mapToDomain()

        // Then
        assertEquals(null, result.htmlUrl)
    }

    @Test
    fun `test List of GitUser mapToDomain`() {
        // Given
        val gitUsers = listOf(
            GitUser(
                id = 1L,
                login = "user1",
                avatarUrl = "url1",
                htmlUrl = "html1"
            ),
            GitUser(
                id = 2L,
                login = "user2",
                avatarUrl = "url2",
                htmlUrl = "html2"
            ),
            GitUser(
                id = 3L,
                login = "user3",
                avatarUrl = null,
                htmlUrl = null
            )
        )

        // When
        val result = gitUsers.mapToDomain()

        // Then
        assertEquals(3, result.size)
        assertEquals(1L, result[0].id)
        assertEquals("user1", result[0].login)
        assertEquals(2L, result[1].id)
        assertEquals("user2", result[1].login)
        assertEquals(3L, result[2].id)
        assertEquals(null, result[2].avatarUrl)
        assertEquals(null, result[2].htmlUrl)
    }

    @Test
    fun `test empty List of GitUser mapToDomain`() {
        // Given
        val gitUsers = emptyList<GitUser>()

        // When
        val result = gitUsers.mapToDomain()

        // Then
        assertEquals(0, result.size)
    }
}