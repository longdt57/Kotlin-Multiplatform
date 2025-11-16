package leegroup.module.designsystem.support.extensions

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

suspend fun getString(msgId: StringResource?, debugMsg: String?): String? {
    return msgId?.let { getString(it) } ?: debugMsg
}