package com.example.hookahlounge.data.mappers

import com.example.hookahlounge.data.datastore.UserPreference
import com.example.hookahlounge.data.dto.datasource.UserDto
import com.example.hookahlounge.domain.model.User

fun UserPreference.toModel(): User = User(
    login = login,
    authToken = token,
    currentWorkplace = workplaceId
)

fun User.toPreference(): UserPreference {
    return UserPreference(
        login = login,
        token = authToken,
        workplaceId = currentWorkplace
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        login = login,
        password = password,
        token = authToken
    )
}


fun UserDto.toModel(): User = User(login = login, password = password, authToken = token)
