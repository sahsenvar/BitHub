package com.core.common.model

import java.io.IOException

sealed class NetworkExceptions : IOException(){
    data object NetworkUnavailableException : NetworkExceptions()
}