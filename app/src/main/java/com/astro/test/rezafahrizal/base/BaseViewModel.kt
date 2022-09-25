package com.astro.test.rezafahrizal.base

import androidx.lifecycle.ViewModel
import com.astro.test.rezafahrizal.base.BaseState

abstract class BaseViewModel: ViewModel() {
    abstract fun provideState() : BaseState
}