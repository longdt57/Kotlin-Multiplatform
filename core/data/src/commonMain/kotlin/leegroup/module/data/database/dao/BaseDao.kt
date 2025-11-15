package leegroup.module.data.database.dao

import androidx.room.Delete
import androidx.room.Upsert

/**
 * Base DAO.
 */
interface BaseDao<T> {

    @Upsert
    suspend fun upsert(entity: T): Long

    @Upsert
    suspend fun upsert(vararg entity: T)

    @Upsert
    suspend fun upsert(entities: Collection<T>)

    @Delete
    suspend fun delete(entity: T): Int
}
