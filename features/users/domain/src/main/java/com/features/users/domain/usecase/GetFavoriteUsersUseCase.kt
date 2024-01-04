package com.features.users.domain.usecase

import com.core.common.base.BaseUseCase
import com.features.users.domain.repository.UsersRepository

class GetFavoriteUsersUseCase(
    private val usersRepository: UsersRepository
) : BaseUseCase() {
    operator fun invoke() = runUseCase{
        usersRepository.getFavoriteUsers()
    }
}