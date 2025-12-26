package leegroup.module.gituser.ui.mapper

import kotlinx.coroutines.test.runTest
import leegroup.module.gituser.domain.models.GitUserDetailModel
import leegroup.module.gituser.ui.models.GitUserDetailUiModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GitUserDetailUiMapperTest {

    private lateinit var mapper: GitUserDetailUiMapper

    @BeforeTest
    fun setUp() {
        mapper = GitUserDetailUiMapperImpl()
    }

    @Test
    fun `test mapToUiModel maps all fields correctly`() = runTest {
        // Given
        val oldUiModel = GitUserDetailUiModel()
        val model = GitUserDetailModel(
            id = 1L,
            login = "testuser",
            name = "Test User",
            avatarUrl = "https://example.com/avatar.jpg",
            blog = "https://testblog.com",
            location = "San Francisco",
            followers = 150,
            following = 75
        )

        // When
        val result = mapper.mapToUiModel(oldUiModel, model)

        // Then
        assertEquals("Test User", result.name)
        assertEquals("https://example.com/avatar.jpg", result.avatarUrl)
        assertEquals("https://testblog.com", result.blog)
        assertEquals("San Francisco", result.location)
    }

    @Test
    fun `test mapToUiModel uses login when name is null`() = runTest {
        // Given
        val oldUiModel = GitUserDetailUiModel()
        val model = GitUserDetailModel(
            id = 1L,
            login = "testlogin",
            name = null,
            avatarUrl = "avatar",
            blog = "blog",
            location = "location",
            followers = 100,
            following = 50
        )

        // When
        val result = mapper.mapToUiModel(oldUiModel, model)

        // Then
        assertEquals("testlogin", result.name)
    }

    @Test
    fun `test mapToUiModel handles null avatarUrl`() = runTest {
        // Given
        val oldUiModel = GitUserDetailUiModel()
        val model = GitUserDetailModel(
            id = 1L,
            login = "user",
            name = "Name",
            avatarUrl = null,
            blog = "blog",
            location = "location",
            followers = 100,
            following = 50
        )

        // When
        val result = mapper.mapToUiModel(oldUiModel, model)

        // Then
        assertEquals("", result.avatarUrl)
    }

    @Test
    fun `test mapToUiModel handles null blog`() = runTest {
        // Given
        val oldUiModel = GitUserDetailUiModel()
        val model = GitUserDetailModel(
            id = 1L,
            login = "user",
            name = "Name",
            avatarUrl = "avatar",
            blog = null,
            location = "location",
            followers = 100,
            following = 50
        )

        // When
        val result = mapper.mapToUiModel(oldUiModel, model)

        // Then
        assertEquals("", result.blog)
    }

    @Test
    fun `test mapToUiModel formats followers correctly`() = runTest {
        // Given
        val oldUiModel = GitUserDetailUiModel()
        val model = GitUserDetailModel(
            id = 1L,
            login = "user",
            name = "Name",
            avatarUrl = "avatar",
            blog = "blog",
            location = "location",
            followers = 50,
            following = 25
        )

        // When
        val result = mapper.mapToUiModel(oldUiModel, model)

        // Then
        assertEquals("50", result.followers)
        assertEquals("25", result.following)
    }

    @Test
    fun `test mapToUiModel formats large followers with plus`() = runTest {
        // Given
        val oldUiModel = GitUserDetailUiModel()
        val model = GitUserDetailModel(
            id = 1L,
            login = "user",
            name = "Name",
            avatarUrl = "avatar",
            blog = "blog",
            location = "location",
            followers = 150,
            following = 200
        )

        // When
        val result = mapper.mapToUiModel(oldUiModel, model)

        // Then
        assertEquals("100+", result.followers)
        assertEquals("100+", result.following)
    }

    @Test
    fun `test mapToUiModel preserves old values and updates with new`() = runTest {
        // Given
        val oldUiModel = GitUserDetailUiModel(
            login = "oldlogin",
            name = "Old Name",
            avatarUrl = "oldavatar",
            blog = "oldblog",
            location = "oldlocation",
            followers = "50",
            following = "25"
        )
        val model = GitUserDetailModel(
            id = 1L,
            login = "newuser",
            name = "New Name",
            avatarUrl = "newavatar",
            blog = "newblog",
            location = "New Location",
            followers = 75,
            following = 80
        )

        // When
        val result = mapper.mapToUiModel(oldUiModel, model)

        // Then
        assertEquals("New Name", result.name)
        assertEquals("newavatar", result.avatarUrl)
        assertEquals("newblog", result.blog)
        assertEquals("New Location", result.location)
        assertEquals("75", result.followers)
        assertEquals("80", result.following)
    }
}
