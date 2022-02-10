package domain.model

import java.lang.Exception

sealed class ChooseDeviceResult<out T> {
    data class Success<out T>(val data: T) : ChooseDeviceResult<T>()
    data class Error(val exception: Exception) : ChooseDeviceResult<Nothing>()
}