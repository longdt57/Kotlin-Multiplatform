package leegroup.module.gituser.data.local.room

import androidx.room.Dao
import androidx.room.Query
import leegroup.module.data.database.dao.BaseDao
import leegroup.module.gituser.data.models.GitUserDetail

@Dao
internal interface GitUserDetailDao : BaseDao<GitUserDetail> {

    @Query("SELECT * FROM gituserdetail WHERE login = :login")
    suspend fun getUserDetail(login: String): GitUserDetail?
}
