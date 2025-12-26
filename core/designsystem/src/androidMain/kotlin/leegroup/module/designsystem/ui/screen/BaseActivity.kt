package leegroup.module.designsystem.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowInsetsControllerCompat

open class BaseActivity : ComponentActivity() {

    private val insetsController: WindowInsetsControllerCompat? by lazy {
        window?.let { window -> WindowInsetsControllerCompat(window, window.decorView) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        setLightStatusBar()
        super.onCreate(savedInstanceState)
    }

    private fun setLightStatusBar() {
        insetsController?.isAppearanceLightStatusBars = true
    }

}