//package com.astro.test.rezafahrizal.base
//
//import android.view.View
//import android.view.Window
//import androidx.databinding.ObservableInt
//import com.astro.test.rezafahrizal.extension.Extension.disableScreenAction
//import com.astro.test.rezafahrizal.extension.Extension.enableScreenAction
//import com.astro.test.rezafahrizal.ui.items.loadBar.LoadBarViewModel
//import com.astro.test.rezafahrizal.ui.items.noDataView.NoDataViewModel
//
//abstract class BaseListViewModel : BaseViewModel() {
//    abstract override fun provideState(): BaseState
//    private lateinit var loadViewModel: LoadBarViewModel
//    private lateinit var noDataViewModel: NoDataViewModel
//    private lateinit var window: Window
//    private var contentScreenVisibility = ObservableInt()
//
//    // start view model
//    fun baseListStart(
//        window: Window,
//        contentScreenVisibility: ObservableInt,
//        loadViewModel: LoadBarViewModel,
//        noDataViewModel: NoDataViewModel,
//        state: BaseState
//    ) {
//        this.loadViewModel = loadViewModel
//        this.noDataViewModel = noDataViewModel
//
//        this.loadViewModel.start()
//        this.noDataViewModel.start()
//
//        this.window = window
//        this.contentScreenVisibility = contentScreenVisibility
//    }
//
//    // show screen content
//    fun showScreenContent() {
//        hideAllScreen()
//        contentScreenVisibility.set(View.VISIBLE)
//        window.enableScreenAction()
//    }
//
//    // show load bar
//    fun showLoadBar() {
//        hideAllScreen()
//        loadViewModel.viewVisibility.set(View.VISIBLE)
//        window.disableScreenAction()
//    }
//
//    // hide load bar
//    fun hideLoadBar() {
//        loadViewModel.viewVisibility.set(View.GONE)
//        window.enableScreenAction()
//    }
//
//
//    // show no data
//    fun showNoData() {
//        hideAllScreen()
//        noDataViewModel.viewVisibility.set(View.VISIBLE)
//        window.enableScreenAction()
//    }
//
//    // hide all screen
//    fun hideAllScreen() {
//        contentScreenVisibility.set(View.GONE)
//        loadViewModel.viewVisibility.set(View.GONE)
//        noDataViewModel.viewVisibility.set(View.GONE)
//    }
//
//    abstract fun onDestroy()
//
//}