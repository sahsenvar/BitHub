package com.core.api.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.api.local.dao.UsersDao
import com.core.api.local.entity.FavoriteUserEntity

@Database(
    entities = [FavoriteUserEntity::class],
    version = 1
)
abstract class UsersDB: RoomDatabase() {

    companion object {
        const val NAME = "users.db"
    }

    abstract val usersDao: UsersDao

}