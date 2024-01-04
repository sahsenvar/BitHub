package com.core.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.core.common.interfaces.UiComponentSetter
import com.core.common.enums.ToolbarType
import com.core.common.interfaces.StateObserver
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
abstract class BaseFragment<
        VB : ViewBinding,
        STATE : BaseState,
        INTENT : BaseIntent,
        VM : BaseViewModel<STATE, INTENT>>
    : Fragment(), StateObserver<STATE> {
    private lateinit var _viewState: STATE
    abstract val viewModel: VM
    open val toolbar: Toolbar? = null
    open val bottomBarVisibility: Boolean = false

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    protected lateinit var TAG: String

    private lateinit var uiComponentSetter: UiComponentSetter
    protected abstract fun onCreationFinished()
    protected abstract fun initListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collect {
                observeState(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UiComponentSetter)
            uiComponentSetter = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding()
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setToolbar(toolbar)
        setBottomBarVisibility(bottomBarVisibility)
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        onCreationFinished()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun setLoadingVisibility(visibility: Boolean) {
        uiComponentSetter.setLoadingVisibility(visibility)
    }

    protected fun setBottomBarVisibility(visibility: Boolean) {
        uiComponentSetter.setBottomNavBarVisibility(visibility)
    }

    protected fun registerSearchQueryChangeListener(onQueryTextSubmit: ((String?) -> Boolean)? = null, onQueryTextChange: ((String?) -> Boolean)? = null){
        uiComponentSetter.registerSearchQueryChangeListener(onQueryTextSubmit, onQueryTextChange)
    }

    private fun setToolbar(toolbar: Toolbar?) {
        uiComponentSetter.setToolbar(toolbar?.title, toolbar?.type ?: ToolbarType.Title)
    }



    @Suppress("UNCHECKED_CAST")
    private fun Fragment.viewBinding(): VB {
        lateinit var bnd: VB
        runCatching {
            TAG = "GitHub-" + this::class.java.simpleName
            val type = javaClass.genericSuperclass
            val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
            val method = clazz.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            bnd = method.invoke(null, layoutInflater, null, false) as VB
        }.onFailure {
            throw (Throwable("denoted viewBinding has not found. Please check your fragment and it's view "))
        }
        return bnd
    }

    protected fun Fragment.showErrorDialog(message: String, detail: String?) {
        val messageTextView = TextView(context).apply {
            text = message
            movementMethod = ScrollingMovementMethod()
            setPadding(50, 40, 50, 0)
        }
        fun showDetail() {
            if (messageTextView.text.toString() == message) {
                messageTextView.text = "$message\n\nDetay: ${detail ?: "detay bulunmuyor"}"
            } else {
                messageTextView.text = message
            }
        }

        val dialog = AlertDialog.Builder(this.requireContext())
            .setTitle("HATA!")
            .setView(messageTextView)
            .setPositiveButton("Detay", null)
            .setNegativeButton("Kapat") { dialog, _ -> dialog.cancel() }
            .setCancelable(true)
            .create()
            dialog.setOnShowListener {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                    it.visibility = View.GONE
                    showDetail()
                }
            }
            dialog.show()
    }

}
data class Toolbar(val title: String, val type: ToolbarType = ToolbarType.Title)