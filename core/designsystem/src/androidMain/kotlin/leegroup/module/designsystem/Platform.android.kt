package leegroup.module.designsystem

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.net.toUri
import org.koin.java.KoinJavaComponent.getKoin

class AndroidDesignPlatform(private val context: Context) : DesignPlatform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}

actual fun getDesignPlatform(): DesignPlatform = AndroidDesignPlatform(getKoin().get<Context>())