package com.core.common.interfaces

import com.core.common.enums.ToolbarType


interface UiComponentSetter {
    fun setLoadingVisibility(isVisible: Boolean)
    fun setToolbar(title: String? = null, type: ToolbarType = ToolbarType.Title)
    fun setBottomNavBarVisibility(isVisible: Boolean)
    fun registerSearchQueryChangeListener(onQueryTextSubmit: ((String?) -> Boolean)?, onQueryTextChange: ((String?) -> Boolean)?)
}