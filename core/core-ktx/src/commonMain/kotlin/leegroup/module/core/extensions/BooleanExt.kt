package leegroup.module.core.extensions

val Boolean?.orFalse get() = this ?: false
val Boolean?.orTrue get() = this ?: true