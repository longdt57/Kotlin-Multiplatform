package leegroup.module.core

import platform.Foundation.NSLog

actual fun platform() = "iOS"

actual object Log {
    actual fun d(tag: String, msg: String) = NSLog("$tag: $msg")
    actual fun i(tag: String, msg: String) = NSLog("$tag: $msg")

    actual fun e(tag: String, msg: String, throwable: Throwable?) =
        NSLog("$tag: $msg ${throwable?.message ?: ""}")
}

actual abstract class Context

actual class Intent