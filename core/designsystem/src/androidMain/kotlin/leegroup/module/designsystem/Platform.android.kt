package leegroup.module.designsystem

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.getKoin

class AndroidPlatform(private val context: Context) : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}

actual fun getPlatform(): Platform {
    val context = getKoin().get<Context>()
    return AndroidPlatform(context)
}