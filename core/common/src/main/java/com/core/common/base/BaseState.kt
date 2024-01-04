package com.core.common.base

abstract class BaseState{
    data class Loading(val isEnable: Boolean) : BaseState()
}