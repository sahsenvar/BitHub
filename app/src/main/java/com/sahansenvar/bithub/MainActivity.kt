package com.sahansenvar.bithub

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.core.common.base.BaseActivity
import com.core.common.enums.ToolbarType
import com.sahansenvar.bithub.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onStart() {
        super.onStart()
        binding.bottomNavBar.setupWithNavController(findNavController(R.id.fragmentContainerView))
        binding.bottomNavBar.visibility = View.GONE
    }

    override fun setToolbar(title: String?, type: ToolbarType) {
        if (title.isNullOrBlank())
            binding.toolbar.visibility = View.GONE
        else {
            binding.toolbar.visibility = View.VISIBLE
            binding.toolbar.title = title
            binding.toolbar.menu.clear()
            when (type) {
                ToolbarType.Title -> binding.toolbar.inflateMenu(R.menu.toolbar_title_menu)
                ToolbarType.TitleSearch -> {
                    binding.toolbar.inflateMenu(R.menu.toolbar_title_search_menu)
                }
            }
        }
    }

    override fun setLoadingVisibility(isVisible: Boolean) {
        if (isVisible)
            binding.loadingBar.visibility = View.VISIBLE
        else
            binding.loadingBar.visibility = View.GONE
    }

    override fun setBottomNavBarVisibility(isVisible: Boolean) {
        if (isVisible)
            binding.bottomNavBar.visibility = View.VISIBLE
        else
            binding.bottomNavBar.visibility = View.GONE
    }

    override fun registerSearchQueryChangeListener(onQueryTextSubmit: ((String?) -> Boolean)?, onQueryTextChange: ((String?) -> Boolean)?) {
        val searchView = binding.toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return onQueryTextSubmit?.invoke(query) ?: false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return onQueryTextChange?.invoke(newText) ?: false
            }
        })

    }
}