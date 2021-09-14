package com.medi.comm.network.result

import me.hgj.jetpackmvvm.demo.data.repository.request.NetException


/**
 * 网络请求结果
 */
class Result<T> {
    var value: T? = null
        private set
     var error: NetException?=null
        private set

    fun initSuc(value:T):Result<T>{
        this.value=value
        return this
    }
    fun initErr(error:NetException):Result<T>{
        this.error=error
        return this
    }

    operator fun component1(): T? {
        return value
    }

    operator fun component2(): NetException? {
        return error
    }

    companion object {

        fun <T> of(value: T): Result<T> {
            return  Result<T>().initSuc( value)
        }
        fun <T> of(error: NetException): Result<T> {
            return Result<T>().initErr(error)
        }
    }
}
