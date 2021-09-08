package com.hencoder.coroutinescamp.oprators

import kotlin.coroutines.*

/**
 * author: taolipeng
 * time:   2021/4/15
 * https://blog.csdn.net/catzifeng/article/details/108304338
 */
fun main() {
    //普通挂起函数
    val suspendFun1 = suspend {
        println("返回 hello结果")
        "hello"
    }
    //2. 创建协程
    var coroutine = suspendFun1.createCoroutine(object : Continuation<String> {
        override fun resumeWith(result: Result<String>) {
            println("MyCoroutine 回调resumeWith 返回的结果 " + result.getOrNull())
        }
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

    })

    //3. 执行协程
    coroutine.resume(Unit)


//    //带参数的挂起函数(抽象版)
//    val suspendFun2 = fun(str: String) = suspend {
//        println(str)
//    }
//
//    suspendFun2("suspendFun2")
//
//    //带参数的挂起函数(便于理解版)
//    var suspendFun3 = createSuspendWithParam("suspendFun3")


}


//带参数的挂起函数(便于理解版)
//fun createSuspendWithParam(str: String) = suspend {
//    print(str)
//}