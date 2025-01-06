package leegroup.app.kmm.gituser.support.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import leegroup.app.kmm.gituser.R
import timber.log.Timber

fun Context.stringNotSet() = getString(R.string.not_set)

fun Context.formatAndOpenUrl(url: String) {
    val formattedUrl = url.formattedUrl()
    if (formattedUrl.isNotBlank() && Patterns.WEB_URL.matcher(formattedUrl).matches()) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrl))
        startActivity(intent)
    } else {
        Timber.d("Invalid URL: $url")
    }
}
