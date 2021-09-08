package com.hencoder.coroutinescamp.oprators

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.intrinsics.startCoroutineCancellable
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

/**
 * author: taolipeng
 * time:   2021/4/13
 * https://blog.csdn.net/weixin_40888127/article/details/107641722
 * InlineOnly
 *      -> https://stackoverflow.com/questions/45763075/what-is-the-inlineonly-annotation
 *
 *    Function1
 *     按照流程 --> 直接执行了 invokeSuspend
 *
 *     简易流程文章  demo -> SuspendDemo.kt
 *
 *     operator -> operatorDemo.kt
 *
 *     但是有一个问题   suspend CoroutineScope.() 这个关键字  ->  示例 MyCoroutine2.kt
 *     其实会直接调用invoke
 *       -> 因为这个 到底调用谁 而纠结

 *     SuspendLambda
 *      ->
 *
 *   demo 1 and demo2  结果相同  执行流程是什么？
 *
 *   function 1 编程了  SuspendLambda -> 强壮
 *
 *
 */
class MyCoroutine() : Continuation<String> {
    override fun resumeWith(result: Result<String>) {
        println("MyCoroutine 回调resumeWith 返回的结果 " + result.getOrNull())
    }

    override val context: CoroutineContext
        get() = kotlin.coroutines.EmptyCoroutineContext
}


@InternalCoroutinesApi
fun testOne() {
    val myCoroutineFun: suspend () -> String = {
        //1.协程执行计算的地方
        println("返回 hello结果")
        "hello"
    }
    val myCoroutine = MyCoroutine()
    //对应源码中 block.startCoroutineCancellable(receiver, completion)
    myCoroutineFun.startCoroutineCancellable(myCoroutine)
}

@InternalCoroutinesApi
fun main() {
    testOne()
}

