package com.axweb.chatone.core

import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.axweb.chatone.main.presenter.MainContract


fun Context.showError(message: String) {
    AlertDialog.Builder(this).apply {
        setTitle("Error")
        setMessage(message)
        setPositiveButton("OK", null)
    }.show()
}

fun Fragment.setTitleToolbar(title: String) {
    val act = activity
    if (act is MainContract.View) {
        act.setTitleToolbar(title)
    }
}




@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun VectorDrawable.toBitmap(): Bitmap? {
    val bitmap = Bitmap.createBitmap(
        intrinsicWidth,
        intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.getHeight())
    draw(canvas)
    return bitmap
}

fun Context.toBitmap(drawableId: Int): Bitmap? {
    return when (val drawable = ContextCompat.getDrawable(this, drawableId)) {
        is BitmapDrawable -> {
            drawable.bitmap
        }
        is VectorDrawable? -> {
            drawable?.toBitmap()
        }
        else -> {
            throw IllegalArgumentException("unsupported drawable type")
        }
    }
}