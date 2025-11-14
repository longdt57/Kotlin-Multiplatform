package leegroup.module.gituser

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override fun openUrl(url: String) {
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        appContext.startActivity(intent)
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()