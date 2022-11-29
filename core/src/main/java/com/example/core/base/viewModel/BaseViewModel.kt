package com.example.core.base.viewModel

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class BaseViewModel () : ViewModel() {

    @CallSuper
    open fun setUp(bundle: Bundle?) {
    }

    @CallSuper
    open fun setUp() {
    }
}