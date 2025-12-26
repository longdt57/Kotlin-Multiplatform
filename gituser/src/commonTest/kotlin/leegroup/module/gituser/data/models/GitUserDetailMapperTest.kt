package leegroup.module.gituser.data.models

import kotlin.test.Test
import kotlin.test.assertEquals

class GitUserDetailMapperTest {

    @Test
    fun `test GitUserDetail mapToDomain maps all fields correctly`() {
        // Given
        val gitUserDetail = GitUserDetail(
            id = 12345L,
            login = "testuser",
            name = "Test User",
            avatarUrl = "https://example.com/avatar.jpg",
            blog = "https://testblog.com",
            location = "San Francisco",
            followers = 150,
            following = 75
        )

        // When
        val result = gitUserDetail.mapToDomain()

        // Then
        assertEquals(12345L, result.id)
        assertEquals("testuser", result.login)
        assertEquals("Test User", result.name)
        assertEquals("https://example.com/avatar.jpg", result.avatarUrl)
        assertEquals("https://testblog.com", result.blog)
        assertEquals("San Francisco", result.location)
        assertEquals(150, result.followers)
        assertEquals(75, result.following)
    }

    @Test
    fun `test GitUserDetail mapToDomain with null login defaults to empty string`() {
        // Given
        val gitUserDetail = GitUserDetail(
            id = 12345L,
            login = null,
            name = "Test User",
            avatarUrl = "https://example.com/avatar.jpg",
            blog = "https://testblog.com",
            location = "San Francisco",
            followers = 150,
            following = 75
        )

        // When
        val result = gitUserDetail.mapToDomain()

        // Then
        assertEquals("", result.login)
    }

    @Test
    fun `test GitUserDetail mapToDomain with null name`() {
        // Given
        val gitUserDetail = GitUserDetail(
            id = 12345L,
            login = "testuser",
            name = null,
            avatarUrl = "https://example.com/avatar.jpg",
            blog = "https://testblog.com",
            location = "San Francisco",
            followers = 150,
            following = 75
        )

        // When
        val result = gitUserDetail.mapToDomain()

        // Then
        assertEquals(null, result.name)
    }

    @Test
    fun `test GitUserDetail mapToDomain with null followers defaults to 0`() {
        // Given
        val gitUserDetail = GitUserDetail(
            id = 12345L,
            login = "testuser",
            name = "Test User",
            avatarUrl = "https://example.com/avatar.jpg",
            blog = "https://testblog.com",
            location = "San Francisco",
            followers = null,
            following = 75
        )

        // When
        val result = gitUserDetail.mapToDomain()

        // Then
        assertEquals(0, result.followers)
    }

    @Test
    fun `test GitUserDetail mapToDomain with null following defaults to 0`() {
        // Given
        val gitUserDetail = GitUserDetail(
            id = 12345L,
            login = "testuser",
            name = "Test User",
            avatarUrl = "https://example.com/avatar.jpg",
            blog = "https://testblog.com",
            location = "San Francisco",
            followers = 150,
            following = null
        )

        // When
        val result = gitUserDetail.mapToDomain()

        // Then
        assertEquals(0, result.following)
    }

    @Test
    fun `test GitUserDetail mapToDomain with all nullable fields null`() {
        // Given
        val gitUserDetail = GitUserDetail(
            id = 12345L,
            login = null,
            name = null,
            avatarUrl = null,
            blog = null,
            location = null,
            followers = null,
            following = null
        )

        // When
        val result = gitUserDetail.mapToDomain()

        // Then
        assertEquals(12345L, result.id)
        assertEquals("", result.login)
        assertEquals(null, result.name)
        assertEquals(null, result.avatarUrl)
        assertEquals(null, result.blog)
        assertEquals(null, result.location)
        assertEquals(0, result.followers)
        assertEquals(0, result.following)
    }
}
