package domain.model

import java.lang.Exception

sealed class ChooseRequestResult<out T> {
    data class Success<out T>(val data: T) : ChooseRequestResult<T>()
    data class Error(val exception: Exception) : ChooseRequestResult<Nothing>()
}