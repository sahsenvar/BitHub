package com.core.api.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.api.local.entity.FavoriteUserEntity

@Dao
interface UsersDao {

    @Query("select * from favorite_users")
    suspend fun getFavoriteUsers(): List<FavoriteUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteUser(user: FavoriteUserEntity)

    @Delete
    suspend fun removeFavoriteUser(user: FavoriteUserEntity)

    @Query("select * from favorite_users where username = :username")
    suspend fun getFavoriteUserByUsername(username: String): FavoriteUserEntity?

}