package com.example.core.extensionFunctions

import android.util.Log
import com.example.core.BuildConfig

fun Any.tag(): String = this::class.java.simpleName

fun Any.logInfo(message: String?) {
    if (BuildConfig.DEBUG) {
        Log.i(tag(), buildLogMsg(message))
    }
}

fun Any.logError(message: String?) {
    if (BuildConfig.DEBUG) {
        Log.e(tag(), buildLogMsg(message))
    }
}

@SuppressWarnings("MagicNumber")
fun buildLogMsg(message: String?): String {
    val ste = Thread.currentThread().stackTrace[4]
    val sb = StringBuilder()
    sb.append("[")
    sb.append(ste.fileName)
    sb.append("::")
    sb.append(ste.methodName)
    sb.append("]")
    sb.append(message)
    return sb.toString()
}