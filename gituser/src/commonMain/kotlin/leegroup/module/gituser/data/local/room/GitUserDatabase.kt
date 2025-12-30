package leegroup.module.gituser.data.local.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import leegroup.module.gituser.data.models.GitUser
import leegroup.module.gituser.data.models.GitUserDetailEntity

@Database(entities = [GitUser::class, GitUserDetailEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
internal abstract class GitUserDatabase : RoomDatabase() {
    abstract fun gitUserDao(): GitUserDao
    abstract fun gitUserDetailDao(): GitUserDetailDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
internal expect object AppDatabaseConstructor : RoomDatabaseConstructor<GitUserDatabase> {
    override fun initialize(): GitUserDatabase
}