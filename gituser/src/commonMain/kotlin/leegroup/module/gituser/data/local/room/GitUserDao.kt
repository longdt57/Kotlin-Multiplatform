package leegroup.module.gituser.data.local.room

import androidx.room.Dao
import androidx.room.Query
import leegroup.module.data.database.dao.BaseDao
import leegroup.module.gituser.data.models.GitUser

@Dao
internal interface GitUserDao : BaseDao<GitUser> {

    @Query("SELECT * FROM GitUser WHERE id > :since ORDER BY id LIMIT :perPage")
    suspend fun getUsers(since: Int, perPage: Int): List<GitUser>
}