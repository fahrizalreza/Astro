package com.astro.test.rezafahrizal.module

import com.astro.test.rezafahrizal.ui.user.UserActivityState
import org.koin.dsl.module

val stateModule = module {
    factory { UserActivityState() }
}