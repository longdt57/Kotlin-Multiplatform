package leegroup.module.core.extensions

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout

suspend inline fun <T> suspendCancellableCoroutineWithTimeOut(
    timeMillis: Long,
    crossinline block: (CancellableContinuation<T>) -> Unit
): T {
    return withTimeout(timeMillis) {
        suspendCancellableCoroutine(block)
    }
}