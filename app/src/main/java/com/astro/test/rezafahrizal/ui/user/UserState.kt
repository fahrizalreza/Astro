package com.astro.test.rezafahrizal.ui.user

import androidx.lifecycle.MutableLiveData
import com.astro.test.rezafahrizal.base.BaseState
import com.astro.test.rezafahrizal.model.UserLocal

class UserActivityState : BaseState {
    val currentState = MutableLiveData<UserState>()
}

sealed class UserState {
    object Initialize : UserState()
    data class FilterList(val char: CharSequence?) : UserState()
    data class OnClickFave(val user: UserLocal?, val mode: String) : UserState()
    data class OnChecked(val buttonId: Int) : UserState()
    object ShowScreenContent : UserState()
    data class OnClickReload(val lastState: UserState) : UserState()
    data class NoResponse(val noResponseState: UserState) : UserState()
}