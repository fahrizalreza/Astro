package com.astro.test.rezafahrizal.ui.items.noResponseView

import android.view.View
import androidx.databinding.ObservableInt
import com.astro.test.rezafahrizal.base.BaseItemViewModel
import com.astro.test.rezafahrizal.base.BaseState
import com.astro.test.rezafahrizal.ui.user.UserActivityState
import com.astro.test.rezafahrizal.ui.user.UserState

class NoResponseViewModel : BaseItemViewModel() {
    private lateinit var state: BaseState
    private lateinit var lastState: UserState
    val viewVisibility = ObservableInt()

    fun start(state: BaseState) {
        this.state = state
        viewVisibility.set(View.GONE)
    }

    fun setState(lastState: UserState) {
        this.lastState = lastState
    }

    fun onClickReload(view: View) {
        when (state) {
            is UserActivityState -> (state as UserActivityState).currentState.value = UserState.OnClickReload(lastState)
        }

    }
}