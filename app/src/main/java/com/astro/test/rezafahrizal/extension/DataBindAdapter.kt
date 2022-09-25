package com.astro.test.rezafahrizal.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.astro.test.rezafahrizal.R
import com.astro.test.rezafahrizal.annotation.DataTypeAnnotation
import com.astro.test.rezafahrizal.base.BaseState
import com.astro.test.rezafahrizal.extension.ViewExtension.loadCircleImage
import com.astro.test.rezafahrizal.extension.ViewExtension.loadImage
import com.astro.test.rezafahrizal.model.UserLocal
import com.astro.test.rezafahrizal.ui.user.UserActivityState
import com.astro.test.rezafahrizal.ui.user.UserState

object DataBindAdapter {

//    @BindingAdapter("app:afterTextChangedListener")
//    @JvmStatic
//    fun afterTextChangedListener(editText: EditText, viewModel: UserViewModel?) {
//        editText.afterTextChanged { editChar -> viewModel?.postState(UserState.FilterList(editChar)) }
//    }
//
    @BindingAdapter("app:loadImage")
    @JvmStatic
    fun setImage(
        view: ImageView,
        uri: Int?
    ) {
        if (uri == null) return
        view.loadImage(uri)
    }

    @BindingAdapter("app:loadImage")
    @JvmStatic
    fun setImage(
        view: ImageView,
        uri: String?
    ) {
        view.loadImage(uri)
    }

    @BindingAdapter("app:loadCircleImage")
    @JvmStatic
    fun setCircleImage(
        view: ImageView,
        url: String?
    ) {
        view.loadCircleImage(url)
    }

    @BindingAdapter(value = ["app:onClick", "app:userData"])
    @JvmStatic
    fun onClick(
        view: ImageView,
        state: BaseState?,
        user: UserLocal?
    ) {
        view.setOnClickListener {
            when (state) {
                is UserActivityState -> {
                    if (user?.favourite == 0) {
                        state.currentState.value =
                            UserState.OnClickFave(user, DataTypeAnnotation.FaveMode.INSERT)
                        view.setImageResource(R.drawable.ic_favorite)
                    } else {
                        state.currentState.value =
                            UserState.OnClickFave(user, DataTypeAnnotation.FaveMode.DELETE)
                        view.setImageResource(R.drawable.ic_favorite_border)
                    }
                }
            }
        }
    }

//    @BindingAdapter("app:onChecked")
//    @JvmStatic
//    fun onChecked(radio: RadioGroup, viewModel: UserViewModel?) {
//        val intSelectButton: Int = radio.checkedRadioButtonId
//
//        radio.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radio, id ->
//            viewModel?.setState(UserState.OnChecked(intSelectButton))
//        })
//    }

}