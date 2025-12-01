package leegroup.module.core

actual fun platform() = "Android"

actual object Log {
    actual fun d(tag: String, msg: String) {
        runCatching { // For commonTest
            android.util.Log.d(tag, msg)
        }
    }

    actual fun i(tag: String, msg: String) {
        runCatching { // For commonTest
            android.util.Log.i(tag, msg)
        }
    }

    actual fun e(tag: String, msg: String, throwable: Throwable?) {
        runCatching { // For commonTest
            android.util.Log.e(tag, msg, throwable)
        }
    }
}

actual typealias Context = android.content.Context
actual typealias Intent = android.content.Intent
