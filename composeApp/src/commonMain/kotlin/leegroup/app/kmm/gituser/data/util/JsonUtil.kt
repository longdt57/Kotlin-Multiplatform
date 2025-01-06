package leegroup.app.kmm.gituser.data.util

import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal object JsonUtil {

    val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    inline fun <reified T> decodeFromString(value: String): T? {
        return try {
            json.decodeFromString<T>(value)
        } catch (ex: SerializationException) {
            null
        } catch (ex: IllegalArgumentException) {
            null
        }
    }

    inline fun <reified T> encodeToString(value: T): String {
        return json.encodeToString(value)
    }
}
