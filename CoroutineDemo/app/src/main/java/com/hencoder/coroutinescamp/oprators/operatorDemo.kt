package com.hencoder.coroutinescamp.oprators

import kotlin.coroutines.Continuation
import kotlin.coroutines.startCoroutine

/**
 * author: taolipeng
 * time:   2021/4/12
 * 调用操作符（Invoke operator）
 * a() a.invoke()
 */
fun main() {
    val request = OperatorDemo()

    request{

    }
//    family {
//        addMember("...")
//    }
}

class OperatorDemo {

    fun addMember(name: String) {
        println("xxxxxx --1111  - " + name)
    }

    public operator fun invoke( body: OperatorDemo.() -> Unit) {
        println("xxxxxx")
        body()
        println("xxxxxx")
    }


}