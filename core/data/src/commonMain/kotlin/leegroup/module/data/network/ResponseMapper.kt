package leegroup.module.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import leegroup.module.data.network.ErrorMapper.mapApiCustomError
import leegroup.module.data.network.ErrorMapper.mapApiError

/**
 * Use object to add to kover
 */
object ResponseMapper {
    fun <T> flowTransform(call: suspend FlowCollector<T>.() -> T) = flow {
        runCatching { call() }
            .onSuccess { result -> emit(result) }
            .onFailure { exception -> throw exception }
    }

    fun <T> Flow<T>.asResult(): Flow<Result<T>> {
        return this
            .map<T, Result<T>> { Result.success(it) }
            .catch { e -> emit(Result.failure<T>(e.mapApiError())) }
    }

    inline fun <T, reified E : Throwable> Flow<T>.asCustomResult(): Flow<Result<T>> {
        return this
            .map<T, Result<T>> { Result.success(it) }
            .catch { e -> emit(Result.failure(e.mapApiCustomError<E>())) }
    }
}
