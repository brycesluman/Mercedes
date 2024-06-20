package org.sluman.mercedes.domain

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetUserDetailsUseCaseUnitTest {
    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `get use details for id`() = runTest {
        given(GetUserDetailsUseCase(userRepository).invoke("1")).willReturn(testItem)
        val response = GetUserDetailsUseCase(userRepository).invoke("1")
        Mockito.verify(userRepository).getUserDetails("1")

        assertEquals(response, testItem)
    }

    private val testItem = UserDetailsDomainEntity(
        login = "test",
        id = 1,
        nodeId = null,
        avatarUrl = "https://somedomain.com/avatar.png",
        gravatarId = null,
        url = null,
        htmlUrl = null,
        followersUrl = null,
        followingUrl = null,
        gistsUrl = null,
        starredUrl = null,
        subscriptionsUrl = null,
        organizationsUrl = null,
        reposUrl = null,
        eventsUrl = null,
        receivedEventsUrl = null,
        type = null,
        siteAdmin = null,
        name = null,
        company = null,
        blog = null,
        location = null,
        email = null,
        hireable = null,
        bio = null,
        twitterUsername = null,
        publicRepos = 52,
        publicGists = 10,
        followers = 3432,
        following = 45,
        createdAt = null,
        updatedAt = null
    )
}