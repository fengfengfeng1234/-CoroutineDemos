package com.hencoder.coroutinescamp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.intrinsics.startCoroutineCancellable
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

/**
 * author: taolipeng
 * time:   2021/4/13
 * https://blog.csdn.net/weixin_40888127/article/details/107641722
 */


/**
 * 从这个示例 可以看出来 ，
 *  MyCoroutine2Kt$$$main ->   operator 这个修饰符
 *      -> new  MyCoroutine2Kt$$$main()-> 就会调用invoke
 * test1 中的 Function2
 *      ->  是通过  .invoke($this$coroutineScope, this) 调用的
 *
 *  重点：
 *      ContinuationImpl ->  invokeSuspend -> MyCoroutine2Kt.test1
 */
suspend fun main(args: Array<String>) {
//    var funObject: (Int, String) -> Int = { a, b ->
//        println(b)
//        a
//    }
//    test(funObject)

//    test { i, s ->
//        println(i
//        println(s)
//        1
//    }

    test1 {
        println("222")
        1
    }
}

suspend fun test1(block: suspend CoroutineScope.() -> Int) {
    var value = coroutineScope {
        println("33333")
        block()
    }
    println("result = $value")
}

/**
 *  猜想:
 *    我外面调用方法 实现了 suspend (Int, String)
 *  block: suspend CoroutineScope.() ->
 */
//suspend fun test(funObject: suspend (Int, String) -> Int) {
//    var value = funObject(4, "99")
//    println(value)
//}



