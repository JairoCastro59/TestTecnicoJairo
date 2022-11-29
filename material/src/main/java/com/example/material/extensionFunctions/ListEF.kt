package com.example.material.extensionFunctions

fun <T> List<T>.isLastItem(position: Int): Boolean {
    return size.minus(1) == position
}