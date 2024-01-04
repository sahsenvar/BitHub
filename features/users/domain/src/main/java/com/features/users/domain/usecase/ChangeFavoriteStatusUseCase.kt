package com.features.users.domain.usecase

import com.core.common.base.BaseUseCase
import com.features.users.domain.model.UserDetailDomainModel
import com.features.users.domain.model.UserDomainModel
import com.features.users.domain.repository.UsersRepository

class ChangeFavoriteStatusUseCase(
    private val usersRepository: UsersRepository
) : BaseUseCase() {
    operator fun invoke(user: UserDomainModel) = runUseCase {
        if(user.isFavorite)
           usersRepository.removeFavoriteUser(user)
        else
            usersRepository.addFavoriteUser(user)
    }
    operator fun invoke(user: UserDetailDomainModel) = runUseCase {
        if(user.isFavorite)
            usersRepository.removeFavoriteUser(user)
        else
            usersRepository.addFavoriteUser(user)
    }
}