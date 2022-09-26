package com.astro.test.rezafahrizal.ui.user

import android.content.Context
import android.util.Log
import android.view.Window
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import com.astro.test.rezafahrizal.annotation.DataTypeAnnotation
import com.astro.test.rezafahrizal.base.BaseListViewModel
import com.astro.test.rezafahrizal.base.BaseState
import com.astro.test.rezafahrizal.extension.RepositoryExtension.applySchedulers
import com.astro.test.rezafahrizal.model.User
import com.astro.test.rezafahrizal.model.UserLocal
import com.astro.test.rezafahrizal.model.UserResponse
import com.astro.test.rezafahrizal.repository.Repository
import com.astro.test.rezafahrizal.services.ApiService
import com.astro.test.rezafahrizal.ui.items.loadBar.LoadBarViewModel
import com.astro.test.rezafahrizal.ui.items.noDataView.NoDataViewModel
import com.astro.test.rezafahrizal.ui.items.noResponseView.NoResponseViewModel
import com.astro.test.rezafahrizal.utilities.Utilities
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


class UserViewModel(
    private val state: UserActivityState,
    private val apiService: ApiService
) : BaseListViewModel() {

    private lateinit var call: Single<UserResponse>
    private val compositeDisposable = CompositeDisposable()
    private var items: List<User?> = mutableListOf<User>()
    var isLoading = ObservableField<Int>()
    var noDataView = ObservableField<Int>()
    val bindAdapter = ObservableField<UserAdapter>()
    private lateinit var adapter: UserAdapter
    private lateinit var repository: Repository
    val contentScreenVisibility = ObservableInt()

    // current state in this activity
    override fun provideState(): BaseState = state

    // start view model setup
    fun start(
        context: Context,
        window: Window,
        loadViewModel: LoadBarViewModel,
        noDataViewModel: NoDataViewModel,
        noResponseViewModel: NoResponseViewModel,
        repository: Repository
    ) {
        baseListStart(
            window,
            contentScreenVisibility,
            loadViewModel,
            noDataViewModel,
            noResponseViewModel,
            state
        )
        this.repository = repository
        adapter = UserAdapter(state, context)
    }

    // retrieve data from connection
    fun loadData(char: String, mode: String) {
        showLoadBar()
        clearTable()
        call = apiService.loadUser(char)
        val disposable = call.applySchedulers().subscribe({ result ->
            Log.e(LOG_USER_CONNECTION, "connect to api master data = \n$result")
            val items = result.items
            if (items.isEmpty()) {
                setState(UserState.NoData)
            } else {
                viewModelScope.launch {
                    repository.saveToLocal(items)
                    val local = repository.retrieveUserLocal(mode)
                    setAdapter(local)
                }
            }

        }, { error ->
            Log.e(
                LOG_USER_CONNECTION, "error connection: ${error.message} "
            )
            showNoResponse(UserState.FilterList(char))

        })
        compositeDisposable.add(disposable)
    }

    // set list table on adapter
    fun setAdapter(items: List<UserLocal?>?) {
        adapter.setFilterList(items)
        adapter.submitList(items)
        bindAdapter.set(adapter)
        setState(UserState.ShowScreenContent)
    }

    // clear list table on adapter
    fun clearAdapter() {
        val list: List<UserLocal?> = emptyList()
        adapter.setFilterList(list)
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
        bindAdapter.set(adapter)
        setState(UserState.ShowScreenContent)
    }

    // edit sort mode
    fun editSort(context: Context, mode: String) {
        viewModelScope.launch {
            val local = repository.retrieveUserLocal(mode)
            setAdapter(local)
        }
    }

    // add to favourite item
    fun addToFave(user: UserLocal?, mode: String) {
        viewModelScope.launch {
            if (mode == DataTypeAnnotation.FaveMode.INSERT)
                repository.addToFave(user)
            else
                repository.removeFromFave(user)
        }
    }

    // clear table
    fun clearTable() {
        viewModelScope.launch {
            repository.clearDatabase()
        }
    }

    // no data
    fun noData() {
        clearAdapter()
        showNoData()
    }

    // set current state
    fun setState(currentState: UserState) {
        state.currentState.value = currentState
    }

    // set current state
    fun postState(currentState: UserState) {
        state.currentState.postValue(currentState)
    }

    // clear memory when process done
    override fun onDestroy() {
        Log.e(LOG_USER_CONNECTION, "Unnecessary memory has been cleaned")
        compositeDisposable.dispose()
    }

    // constant value
    companion object {
        const val LOG_USER_CONNECTION: String = "UserTrace"
        const val KEY_SHARED_PREFERENCES: String = "sharedPreferences"
        const val KEY_FIELD_MODE: String = "keyUser"
    }
}