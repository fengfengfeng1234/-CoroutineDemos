package com.kotlinstate.coroutinehttpsimple

/**
 * Created by luyao
 * on 2019/10/12 11:08
 */
sealed class Result1<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result1<T>()
    data class Error(val exception: Exception) : Result1<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}