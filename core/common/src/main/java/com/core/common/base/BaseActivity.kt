package com.core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.core.common.interfaces.UiComponentSetter
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding>() : AppCompatActivity(), UiComponentSetter {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = viewBinding()
        setContentView(binding.root)
    }



    @Suppress("UNCHECKED_CAST")
    private fun FragmentActivity.viewBinding(): VB {
        lateinit var bnd: VB
        runCatching {
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
            throw (Throwable("denoted viewBinding has not found. Please check your activity and it's view "))
        }
        return bnd
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}