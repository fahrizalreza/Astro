//package com.astro.test.rezafahrizal.base
//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.view.Window
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.annotation.LayoutRes
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import androidx.databinding.ViewDataBinding
//import com.astro.test.rezafahrizal.R
//import com.astro.test.rezafahrizal.extension.Extension.isInternetConnected
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.withContext
//
//abstract class BaseActivity<binding : ViewDataBinding, state : BaseState, viewModel : BaseViewModel>(
//    @LayoutRes val layoutId: Int
//) : AppCompatActivity() {
//
//    protected lateinit var binding: binding
//
//    protected abstract val viewModel: viewModel
//
//    @Suppress("UNCHECKED_CAST")
//    protected val state: state by lazy {
//        viewModel.provideState() as state
//    }
//
//    private var job = Job()
//    val coroutineScope = CoroutineScope(Dispatchers.Main + job)
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
////        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
//        initializedDataBind()
//        initializeView()
//        observeViewState(state)
//    }
//
//    open fun initializeView() {}
//
//    private fun initializedDataBind() {
//        DataBindingUtil.setContentView<binding>(this, layoutId).apply {
//            binding = this
//            lifecycleOwner = this@BaseActivity
////            setVariable(BR.viewModel, viewModel)
////            setVariable(BR.state, state)
//            executePendingBindings()
//        }
//    }
//
//    abstract fun observeViewState(state: state)
//
//    // perform when user press back button
//    override fun onBackPressed() {
//        if (this.localClassName == "ui.user.UserActivity") {
//            openDialog()
//        } else {
//            closePage()
//        }
//    }
//
//    // check internet connection
//    suspend fun isInternetConnected(): Boolean =
//        withContext(Dispatchers.Default) {
//            applicationContext.isInternetConnected()
//        }
//
//    // confirmation box
//    private fun openDialog() {
//        val builder1 = AlertDialog.Builder(this)
//        builder1.setMessage(resources.getString(R.string.exit_app_ind))
//        builder1.setCancelable(true)
//        builder1.setPositiveButton(
//            resources.getString(R.string.yes_ind)
//        ) { dialog, id -> finish() }
//        builder1.setNegativeButton(
//            resources.getString(R.string.no_ind)
//        ) { dialog, id -> dialog.cancel() }
//        val alert = builder1.create()
//        alert.setOnShowListener {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                alert.getButton(AlertDialog.BUTTON_POSITIVE)
//                    .setTextColor(resources.getColor(R.color.black, null))
//                alert.getButton(AlertDialog.BUTTON_NEGATIVE)
//                    .setTextColor(resources.getColor(R.color.black, null))
//            } else {
//
//            }
//        }
//        alert.show()
//    }
//
//    override fun startActivity(intent: Intent?) {
//        super.startActivity(intent)
//    }
//
//    fun closePage() {
//        finish()
//    }
//
//    // pop up message
//    fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//    }
//}
