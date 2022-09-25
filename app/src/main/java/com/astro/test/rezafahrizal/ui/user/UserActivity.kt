package com.astro.test.rezafahrizal.ui.user

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.astro.test.rezafahrizal.R
import com.astro.test.rezafahrizal.annotation.DataTypeAnnotation
import com.astro.test.rezafahrizal.base.BaseActivity
import com.astro.test.rezafahrizal.databinding.UserActivityBind
import com.astro.test.rezafahrizal.repository.Repository
import com.astro.test.rezafahrizal.ui.items.loadBar.LoadBarViewModel
import com.astro.test.rezafahrizal.ui.items.noDataView.NoDataViewModel
import com.astro.test.rezafahrizal.ui.items.noResponseView.NoResponseViewModel
import com.astro.test.rezafahrizal.utilities.Utilities
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent


class UserActivity :
    BaseActivity<UserActivityBind, UserActivityState, UserViewModel>(R.layout.activity_user) {

    override val viewModel: UserViewModel by inject()
    private val loadViewModel: LoadBarViewModel by inject()
    private val noDataViewModel: NoDataViewModel by inject()
    private val noResponseViewModel: NoResponseViewModel by inject()
    private val repository: Repository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setState(UserState.Initialize)
    }

    // monitor all process
    override fun observeViewState(currentState: UserActivityState) {
        state.currentState.observe(this) { currentState ->
            Log.d(
                LOG_USER,
                "current state = ${state.currentState.value?.javaClass?.simpleName}"
            )
            when (currentState) {
                is UserState.Initialize -> initialize()
                is UserState.FilterList -> loadData(currentState.char.toString())
                is UserState.OnChecked -> sortMode()
                is UserState.OnClickFave -> viewModel.addToFave(
                    currentState.user,
                    currentState.mode
                )
                is UserState.ShowScreenContent -> viewModel.showScreenContent()
                is UserState.NoData -> viewModel.noData()
                is UserState.NoResponse -> viewModel.showNoResponse(currentState.noResponseState)
                is UserState.OnClickReload -> viewModel.setState(currentState.lastState)
                else -> viewModel.showScreenContent()
            }
        }
    }

    // initialize first process
    private fun initialize() {
        binding.viewModel = viewModel
        binding.llProgressViewSourceList.viewModel = loadViewModel
        binding.rlSourceListNoDataScreen.viewModel = noDataViewModel
        binding.rlSourceListNoResponseScreen.viewModel = noResponseViewModel

        viewModel.start(
            this,
            window,
            loadViewModel,
            noDataViewModel,
            noResponseViewModel,
            repository
        )
        val pref = Utilities.getDataPref(this, KEY_FIELD_MODE)
        if (pref.isNullOrBlank()) {
            Utilities.saveDataPreference(
                this, KEY_SHARED_PREFERENCES, KEY_FIELD_MODE, DataTypeAnnotation.SortMode.ASC
            )
            binding.rbAscSearch.isChecked = true
        } else {
            if (pref == DataTypeAnnotation.SortMode.ASC) binding.rbAscSearch.isChecked = true
            else binding.rbDescSearch.isChecked = true
        }
        viewModel.clearAdapter()

    }

    // load data from api
    private fun loadData(char: String) {
        lifecycleScope.launch {
            if (isInternetConnected()) {
                val mode = Utilities.getDataPref(this@UserActivity, KEY_FIELD_MODE).toString()
                if (char.isBlank()) viewModel.clearAdapter() else viewModel.loadData(char, mode)
            } else {
                viewModel.setState(UserState.NoResponse(UserState.FilterList(char)))
                showToast(ERROR_NO_INTERNET)
            }
        }
    }

    // sort mode mechanism
    private fun sortMode() {
        if (binding.rbAscSearch.isChecked) {
            Utilities.saveDataPreference(
                this,
                UserViewModel.KEY_SHARED_PREFERENCES,
                UserViewModel.KEY_FIELD_MODE, DataTypeAnnotation.SortMode.ASC
            )
            if (binding.etSourceSearch.text.isNullOrBlank())
                viewModel.clearTable()
            else
                viewModel.editSort(this, DataTypeAnnotation.SortMode.ASC)
        } else {
            Utilities.saveDataPreference(
                this,
                UserViewModel.KEY_SHARED_PREFERENCES,
                UserViewModel.KEY_FIELD_MODE, DataTypeAnnotation.SortMode.DESC
            )
            if (binding.etSourceSearch.text.isNullOrBlank())
                viewModel.clearTable()
            else
                viewModel.editSort(this, DataTypeAnnotation.SortMode.DESC)
        }
    }

    companion object {
        const val LOG_USER: String = "UserTrace"
        const val KEY_SHARED_PREFERENCES: String = "sharedPreferences"
        const val KEY_FIELD_MODE: String = "keyUser"
        const val ERROR_NO_INTERNET = "Anda tidak terhubung dengan internet"
    }
}