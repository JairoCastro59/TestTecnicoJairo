package com.example.material.extensionFunctions

import android.view.View

private const val DELAY_MILLIS = 2000

fun View.setInvisible(isInvisible: Boolean) {
    this.visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
}

fun View.setVisibility(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.avoidDoubleClicks(onClick: () -> Unit) {
    if (!isClickable) {
        return
    }
    isClickable = false
    postDelayed({ isClickable = true }, DELAY_MILLIS.toLong())
    onClick()
}