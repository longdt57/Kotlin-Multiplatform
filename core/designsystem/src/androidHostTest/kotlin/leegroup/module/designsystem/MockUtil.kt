package leegroup.module.designsystem

object MockUtil {
    val serverException: Throwable = java.net.ConnectException()
    val noConnectivityException: Throwable = java.net.UnknownHostException()
}
