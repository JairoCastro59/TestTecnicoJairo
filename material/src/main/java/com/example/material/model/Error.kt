package com.example.material.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.material.R

class Error (
    @StringRes var messageRes: Int? = null,
    var message: String? = null,
    @StringRes var titleRes: Int? = null,
    var title: String? = null,
    @DrawableRes var icon: Int = R.drawable.ic_feedback_warning,
    var action: Action? = null
) {
    class Action(
        @StringRes var actionName: Int? = null,
        var actionId: Int,
        var actionString: String? = null
    )
}