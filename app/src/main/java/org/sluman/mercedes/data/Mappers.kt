package org.sluman.mercedes.data

import org.sluman.mercedes.domain.UserDetailsDomainEntity
import org.sluman.mercedes.domain.UserDomainEntity

fun UserNetworkEntity.toDomain(): UserDomainEntity {
    return UserDomainEntity(
        login = this.login,
        id = this.id,
        avatarUrl = this.avatarUrl
    )
}

fun UserDetailsNetworkEntity.toDomain(): UserDetailsDomainEntity {
    return UserDetailsDomainEntity(
        login = this.login,
        id = this.id,
        avatarUrl = this.avatarUrl,
        company = this.company,
        blog = this.blog,
        location = this.location,
        name = this.name,
        email = this.email,
        twitterUsername = this.twitterUsername,
        followers = this.followers,
        publicRepos = this.publicRepos,

        publicGists = this.publicGists
    )
}