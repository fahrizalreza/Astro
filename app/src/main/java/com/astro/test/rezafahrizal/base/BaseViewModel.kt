package com.astro.test.rezafahrizal.base

import com.astro.test.rezafahrizal.base.BaseState

abstract class BaseViewModel {
    abstract fun provideState() : BaseState
}