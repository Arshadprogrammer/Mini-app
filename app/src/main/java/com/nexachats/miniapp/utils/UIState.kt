package com.nexachats.miniapp.utils

/**
 * @Created by Arshad Khan on 4/22/2025.
 */
sealed class UIState<T> (
    val data: T? = null,
    val message: String = ""
    ) {
    class Success<T>(data: T) : UIState<T>(data)
    class Error<T>(message: String) : UIState<T>(null, message)
    class Loading<T> : UIState<T>()

}