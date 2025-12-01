package leegroup.module.core

expect fun platform(): String

expect object Log {
    fun d(tag: String, msg: String)
    fun i(tag: String, msg: String)
    fun e(tag: String, msg: String, throwable: Throwable? = null)
}

expect abstract class Context
expect class Intent
