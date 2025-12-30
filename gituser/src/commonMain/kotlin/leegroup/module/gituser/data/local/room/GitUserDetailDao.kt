package leegroup.module.gituser.data.local.room

import androidx.room.Dao
import androidx.room.Query
import leegroup.module.data.database.dao.BaseDao
import leegroup.module.gituser.data.models.GitUserDetailEntity

@Dao
internal interface GitUserDetailDao : BaseDao<GitUserDetailEntity> {

    @Query("SELECT * FROM GitUserDetailEntity WHERE login = :login")
    suspend fun getUserDetail(login: String): GitUserDetailEntity?
}
