package com.hencoder.coroutinescamp

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author: taolipeng
 * time:   2021/4/13
 * 协程挂起流程
 *
 */
@InternalCoroutinesApi
fun main() {
    println("main")
    runBlocking {
        test1();

    }
    println("11")



}

suspend fun test1() {
    println("test1")
    delay(100)
    println("test1-end")
}
