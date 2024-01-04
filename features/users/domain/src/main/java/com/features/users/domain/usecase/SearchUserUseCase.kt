package com.features.users.domain.usecase

import com.core.common.base.BaseUseCase
import com.features.users.domain.repository.UsersRepository

class SearchUserUseCase(
    private val usersRepository: UsersRepository
) : BaseUseCase(){
    operator fun invoke(username: String) = runUseCase {
        usersRepository.searchUser(username)
    }
}