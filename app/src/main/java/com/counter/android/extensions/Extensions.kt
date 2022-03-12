package com.counter.android.extensions

import android.app.Activity
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.core.view.WindowInsetsControllerCompat

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity?.statusBarLight() {
    val window = this?.window as Window
    val decorView = window.decorView
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = true
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
}

fun Activity?.statusBarDark() {
    val window = this?.window as Window
    val decorView = window.decorView
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = false
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
}

@Stable
fun Modifier.visible(visibility: Boolean): Modifier {
    return if (visibility) {
        this.then(alpha(1f))
    } else {
        this.then(alpha(0f))
    }
}