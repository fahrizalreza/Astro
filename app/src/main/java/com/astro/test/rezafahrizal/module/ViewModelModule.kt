package com.astro.test.rezafahrizal.module

import com.astro.test.rezafahrizal.ui.items.loadBar.LoadBarViewModel
import com.astro.test.rezafahrizal.ui.items.noDataView.NoDataViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
//    viewModel { UserViewModel(get(), get()) }

    viewModel { LoadBarViewModel() }

    viewModel { NoDataViewModel() }

}