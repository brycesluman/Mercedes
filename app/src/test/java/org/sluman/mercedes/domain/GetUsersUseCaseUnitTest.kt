package org.sluman.mercedes.domain

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Before
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetUsersUseCaseUnitTest {
    @Mock
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `get all users`() = runTest {
        given(GetUsersUseCase(userRepository).invoke()).willReturn(testItems)

        val response = GetUsersUseCase(userRepository).invoke()
        verify(userRepository).getUsers()

        assertEquals(response, testItems)
    }

    private val testItems = listOf(
        UserDomainEntity(
            login = "test",
            id = 1,
            nodeId = "923ry92cb",
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
            siteAdmin = false
        ),
        UserDomainEntity(
            login = "test2",
            id = 2,
            nodeId = "4nbc849283",
            avatarUrl = "https://somedomain.com/avatar2.png",
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
            siteAdmin = false
        )
    )
}